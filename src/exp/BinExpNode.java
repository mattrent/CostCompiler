package exp;

import ast.Node;

public class BinExpNode implements Node {
    private Node left;
    private Node right;
    private String op;

    public BinExpNode(Node left, Node right, String op) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "BinExp: " + op + "\n" + left.toPrint(indent + "\t") + right.toPrint(indent + "\t");
    }
}
