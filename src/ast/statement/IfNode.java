package ast.statement;

import ast.Node;
import ast.exp.BinExpNode;
import ast.statement.CallNode;
import ast.statement.CallServiceNode;
import ast.typeNode.BoolType;
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
            exp.checkVarEQ(e);
        }
        String dec =  "if"+line+"(" + e.get(exp)+","+ e.get(stmT)+","+e.get(stmF)+")";
        String expCost = (this.exp instanceof CallServiceNode)? "nat("+e.get(exp)+")" : "0";
        String decCall =  expCost+",["+ dec +"],[]";

        if (stmF instanceof CallNode &&  (getFunDecNodeByLine(e, line) != null) && Objects.equals(((CallNode) stmF).getId(), getFunDecNodeByLine(e, line).getId()) && exp instanceof BinExpNode) {
            BinExpNode expNode = (BinExpNode) this.exp;
            return decCall + " ).\neq(" + dec + ",nat(" + e.get(stmT) + "),[" + e.get(exp) + expNode.negToEquation(e) + "]).\neq(" + dec + ",0,["+ stmF.toEquation(e)+"][" + e.get(exp) +exp.toEquation(e) +"]).\n";
        } else if (stmT instanceof CallNode &&  getFunDecNodeByLine(e, line)!= null && Objects.equals(((CallNode) stmT).getId(), getFunDecNodeByLine(e, line).getId()) && exp instanceof BinExpNode){
            BinExpNode expNode = (BinExpNode) this.exp;
            return decCall + ").\neq(" + dec + ",0,[" + stmT.toEquation(e) + "], [" + e.get(exp) + exp.toEquation(e) + " ] ).\neq(" + dec + " , nat(" + e.get(stmF) + "),[],[" + e.get(exp) + expNode.negToEquation(e)+"]).\n";
        }else {
            String valStmT = stmT instanceof CallFunNode ? "1" : "nat("+e.get(stmT)+")";
            String valStmF = stmF instanceof CallFunNode ? "1" : "nat("+e.get(stmT)+")";
            valStmT = stmT.toEquation(e);
            valStmF = stmF.toEquation(e);
            return decCall + ").\neq(" + dec + ","+ valStmT +",[],[" + e.get(exp) + "=1]).\neq(" + dec +","+ valStmF+",[],[" + e.get(exp) + "=0]).\n";
        }


        //una volta fatto le equazioni del nodo andiamo a fare quelle per i nodi sottostanti
        //if a cascata
    }
}
