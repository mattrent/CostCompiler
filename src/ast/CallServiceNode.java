package ast;

import gen.*;

import java.util.ArrayList;

public class CallServiceNode implements Node {
    String idCall;
    ArrayList<Node> exp;
    HLCostLanParser.StmContext stm;
    public CallServiceNode(String idCall, ArrayList<Node> exp, HLCostLanParser.StmContext stm) {
        this.idCall = idCall;
        this.exp = exp;
        this.stm = stm;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "CallServiceNode " + idCall + "\n" + indent + "Exp:\n" + exp.toString() + "\n" + indent + "Stm:\n" + stm.toString();
    }
}
