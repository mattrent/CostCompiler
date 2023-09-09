package ast;

import utilities.EnvVar;
import utilities.Environment;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CallServiceNode implements Node {
    String idCall;
    ArrayList<Node> exp;
    Node stm;
    public CallServiceNode(String idCall, ArrayList<Node> exp,Node stm) {
        this.idCall = idCall;
        this.exp = exp;
        this.stm = stm;
    }
    public CallServiceNode(String idCall, ArrayList<Node> exp) {
        this.idCall = idCall;
        this.exp = exp;
        this.stm = stm;
    }
    @Override
    public String toPrint(String indent) {
        return indent + "CallServiceNode " + idCall + "\n" + indent + "Exp:\n" + exp.toString() + "\n" + indent + "Stm:\n" + stm.toString();
    }

    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        e.add(this, String.valueOf(idCall.charAt(0)));
        if(stm != null){
            e = stm.checkVarEQ(e);
        }
        return e;
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        ArrayList<String> errors = new ArrayList<>();
        if(!env.containsDeclaration(idCall)){
            errors.add(idCall +" is not declared");
        }
        if(exp != null) {
            for (Node n : exp) {
                errors.addAll(n.checkSemantics(env));
            }
        }
        if(stm != null){
            errors.addAll(stm.checkSemantics(env));
        }
        return errors;
    }

    @Override
    public String toEquation(EnvVar e) {
        if(stm != null) {
            return e.get(this) + "+" + stm.toEquation(e);
        }else
            return e.get(this);
    }
}
