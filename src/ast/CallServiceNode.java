package ast;

import gen.*;

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
    public Environment checkSemantics(Environment e) {
        e.add(this, String.valueOf(idCall.charAt(0)));
        if(stm != null){
            e = stm.checkSemantics(e);
        }
        return e;
    }

    @Override
    public String toEquation(Environment e) {
        if(stm != null) {
            return e.get(this) + "+" + stm.toEquation(e);
        }else
            return e.get(this);
    }
}
