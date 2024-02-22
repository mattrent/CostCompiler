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
        if (formalParams != null) {
            for (Pair<String, TypeNode> n : formalParams.getFormalParams()) {
                code.append(" (param $").append(n.a).append(" i32)");
            }
        }
        code.append(" (result i32)\n");
        code.append(stm.codeGeneration(offset_idx));
        code.append(")\n");
        return code.toString();
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
