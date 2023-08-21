package ast;

public class IfNode implements Node {

    Node exp;
    Node stmT;
    Node stmF;
    public IfNode(CallServiceNode callServiceNode, Node node, Node node1) {
        this.exp = callServiceNode;
        this.stmT = node;
        this.stmF = node1;
    }
    public IfNode(Node condNode, Node node, Node node1) {
        this.exp = condNode;
        this.stmT = node;
        this.stmF = node1;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "IfNode\n" + exp.toPrint(indent + "\t") + "\n" + stmT.toPrint(indent + "\t") + "\n" + stmF.toPrint(indent + "\t");
    }

}
