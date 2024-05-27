package ast.statement;

import ast.IdNode;
import ast.Node;
import ast.exp.BinExpNode;
import ast.typeNode.TypeNode;
import org.antlr.v4.runtime.misc.Pair;
import utilities.EnvVar;
import utilities.Environment;
import utilities.TypeErrorException;
import ast.statement.CallNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;

public class FunDeclarationNode implements Node {

    private final Node type;
    private final IdNode id;
    private final FormalParams formalParams;
    private final Node stm;
    private String parFun;
    private int line;
    public FunDeclarationNode(Node type, IdNode id, FormalParams formalParams, Node stm, int line) {
        this.type = type;
        this.id = id;
        this.formalParams = formalParams;
        this.stm = stm;
        parFun = null;
        this.line = line;
    }

    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        e.add(this,id.getId());
        stm.checkVarEQ(e);
        return e;
    }

    @Override
    public Node typeCheck(Environment e) throws TypeErrorException {
        e.addDeclaration(id.getId(),this);
        e.openScope();
        if(formalParams != null) {
            formalParams.typeCheck(e);
        }
        if(stm!= null)
            stm.typeCheck(e);
        e.closeScope();
        return type.typeCheck(e);
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        ArrayList<String> errors = new ArrayList<>();
        if(env.containsDeclaration(id.getId()))
            errors.add(id.getId()+" is already declared");
        else
            env.addDeclaration(id.getId(),this);
        env.openScope();
        if(formalParams != null) {
            errors.addAll(formalParams.checkSemantics(env));
        }
        if(stm!= null)
            errors.addAll(stm.checkSemantics(env));

        errors.addAll(type.checkSemantics(env));
        env.closeScope();
        return errors;
    }

    @Override
    public String toEquation(EnvVar e) {

        stm.checkVarEQ(e);
        e.add(this,id.getId());
        String pre = id.getId() + "(" ;


        //check in e if there is recursive call
        Node nodeToRemove = null;
        for (Node n : e.getSet()){
            if(Objects.equals(e.get(n), this.id.getId()) && n instanceof CallNode){
               nodeToRemove = n;
            }
        }

        if(nodeToRemove != null)
            e.remove(nodeToRemove);

        Set<Node> set = e.getSet();
        StringBuilder par = new StringBuilder();
        for(Node n : set){
            if (!(n instanceof FunDeclarationNode ||
                    (n instanceof CallNode) && ((CallNode) n).getId().equals(this.id.getId()))) {
                par.append(e.get(n)).append(",");
            }
        }

        this.parFun = String.valueOf(new StringBuilder(par.substring(0, par.length() - 1)));

        String post = "";
        if(stm instanceof CallNode || stm instanceof ListAssignmentMain){
            post =  "),1,[" + stm.toEquation(e) + "],[]).";
        }else if (stm instanceof BinExpNode){
            post = "),"+ stm.toEquation(e) + ",[],[]).";
        }
        else post =  ")," + stm.toEquation(e) ;

        return "\neq("+ pre + this.parFun + post ;

    }

    @Override
    public String codeGeneration(HashMap<Node, Integer> offset_idx) {
        StringBuilder code = new StringBuilder("(func $" + id.getId());
        // main also receives the first available memory address as first input
        if (id.getId().equals("main")) {
            code.append(" (param $__first_available_FL_ptr i32)");
        }
        if (formalParams != null) {
            for (Pair<String, TypeNode> n : formalParams.getFormalParams()) {
                String[] wasmType = n.b.codeGeneration(offset_idx).split(",");
                if (wasmType.length == 1) {
                    code.append(String.format(" (param $%s %s)", n.a, wasmType[0]));
                } else {
                    code.append(String.format(" (param $%s %s) (param $%s %s)", n.a + "_ptr", wasmType[0], n.a + "_len", wasmType[1]));
                }
            }
        }
        if (this.type == null) {
            code.append(" (result i32)\n");
        } else {
            String wasmReturnType = this.type.codeGeneration(offset_idx).replace(",", " ");
            code.append(String.format(" (result %s)\n", wasmReturnType));
        }

        //TODO: very inefficient, should be done organically in the codeGeneration process and inserted here after
        ArrayList<String> forDeclarations = checkForStms(stm, new ArrayList<String>());
        if (!forDeclarations.isEmpty()) {
            for(String forId: forDeclarations) {
                code.append(String.format("(local $%s i32)\n", forId));
                code.append(String.format("(local $%s_max i32)\n", forId));
            }
            code.append("(local $res i32)\n");
        } else if (checkIfStms(stm)) {
            code.append("(local $res i32)\n");
        }

        if (id.getId().equals("main")) {
            code.append("(local.get $__first_available_FL_ptr)\n");
            code.append("(global.set $__first_available_ptr)\n");
        }
        code.append(stm.codeGeneration(offset_idx));
        if (id.getId().equals("main")) {
            code.append("(global.get $__first_available_ptr)\n"); //write the character "0" to memory
            code.append("(i32.const 48)\n");
            code.append("(i32.store)\n");
            code.append("(global.get $__first_available_ptr)\n");
            code.append("(i32.const 1)\n");
            code.append("(call $__insert_response)\n");
            code.append("(i32.const 0)\n");
            code.append("return\n");
        }
        code.append(")\n");
        return code.toString();
    }

    // NOTE: only if/for/serviceCall are handled, no instant returns or let-in expressions
    private boolean checkIfStms(Node stm) {
        if (stm instanceof ForNode) {
            ForNode forStm = (ForNode)stm;
            return checkIfStms(forStm.stm);
        } else if (stm instanceof IfNode) {
            return true;
        } else if (stm instanceof CallServiceNode) {
            CallServiceNode callStm = (CallServiceNode) stm;
            return checkIfStms(callStm.stm);
        } else {
            return false;
        }
    }

    private ArrayList<String> checkForStms(Node stm, ArrayList<String> acc) {
        if (stm instanceof ForNode) {
            ForNode forStm = (ForNode)stm;
            acc.add(forStm.id);
            return checkForStms(forStm.stm, acc);
        } else if (stm instanceof IfNode) {
            IfNode ifStm = (IfNode)stm;
            return checkForStms(ifStm.stmT, checkForStms(ifStm.stmF, acc));
        } else if (stm instanceof CallServiceNode) {
            CallServiceNode callStm = (CallServiceNode) stm;
            return checkForStms(callStm.stm, acc);
        } else {
            return acc;
        }
    }

    public int getLine() {
        return line;
    }

    public FormalParams getFormalParams() {
        return formalParams;
    }

    public Node getReturnNode(){
        return type;
    }

    public String getId() { return id.getId(); }

    public String getParFun() { return parFun; }

}
