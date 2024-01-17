package ast.statement;

import ast.Node;
import ast.exp.BinExpNode;
import ast.statement.CallNode;
import ast.statement.CallServiceNode;
import ast.typeNode.BoolType;
import org.junit.platform.commons.util.StringUtils;
import utilities.EnvVar;
import utilities.Environment;
import utilities.TypeErrorException;
import utilities.Utils;

import java.util.ArrayList;
import java.util.Objects;

import static utilities.Utils.getFunDecNodeByLine;

public class IfNode implements Node {

    Node exp;
    Node stmT;
    Node stmF;
    int line;
    public IfNode(CallServiceNode callServiceNode, Node node, Node node1, int line) {
        this.exp = callServiceNode;
        this.stmT = node;
        this.stmF = node1;
        this.line = line;
    }
    public IfNode(Node condNode, Node node, Node node1,int line) {
        this.exp = condNode;
        this.stmT = node;
        this.stmF = node1;
        this.line = line;
    }


    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        exp.checkVarEQ(e);
        stmT.checkVarEQ(e);
        stmF.checkVarEQ(e);

        return e;
    }

    @Override
    public Node typeCheck(Environment e) throws TypeErrorException {
        if(!(exp.typeCheck(e) instanceof BoolType &&
            Utils.isSubtype(stmT.typeCheck(e),stmF.typeCheck(e)))){
            throw new TypeErrorException("Incompatible type in if Node");
        }
        return stmT.typeCheck(e);
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        ArrayList<String> errors = new ArrayList<>();

        errors.addAll(exp.checkSemantics(env));
        errors.addAll(stmT.checkSemantics(env));
        errors.addAll(stmF.checkSemantics(env));

        return errors;
    }

    @Override
    public String toEquation(EnvVar e) {

        exp.checkVarEQ(e);
        if(e.get(stmT) == null &&  e.get(stmF)==null) {
            e.add(stmT);
            e.add(stmF);
        }
        if(e.get(exp)==null){
           e.add(exp);
        }
        String dec =  "if"+line+"(" + e.get(exp)+","+ e.get(stmT)+","+e.get(stmF)+")";
        String expCost = (this.exp instanceof CallServiceNode)? "nat("+e.get(exp)+")" : "0";
        String decCall =  expCost+",["+ dec +"],[]";

        String valStmT;
        String valStmF;
        String valExpT;
        String valExpF;
        valExpT =  "[" + e.get(exp) + "=1]"  ;
        valExpF = "[" + e.get(exp) + "=0]" ;
        valStmT = !(stmT instanceof CallNode) ? stmT.toEquation(e)+ ",[]," : "0,["+stmT.toEquation(e)+"],";
        valStmF = !(stmF instanceof CallNode) ? stmF.toEquation(e)+ ",[]," : "0,["+stmF.toEquation(e)+"],";
        if(stmF instanceof ForNode){
            valStmF = stmF.toEquation(e);
            valStmF = valStmF.replaceFirst("\\[\\]",valExpF);
            //remove last 2 character
            valStmF = valStmF.substring(0,valStmF.length()-3);
            valExpF= "";
        }
        if (stmT instanceof ForNode){
            valStmT = stmT.toEquation(e);
            valStmT = valStmT.replaceFirst("\\[\\]",valExpT);
            //remove last 2 character
            valStmT = valStmT.substring(0,valStmT.length()-3);
            valExpT= "";
        }
        return decCall + ").\neq(" + dec + ","+ valStmT+valExpT+").\neq(" + dec +","+ valStmF+valExpF + ").\n";
    }

    @Override
    public String codeGeneration() {
        return "(if\n"+exp.codeGeneration()+"(then\n"+stmT.codeGeneration()+")(else\n"+stmF.codeGeneration()+"\n)";
    }
}
