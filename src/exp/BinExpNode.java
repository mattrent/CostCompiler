package exp;

import utilities.EnvVar;
import ast.Node;
import utilities.Environment;

import java.util.ArrayList;

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

    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        return null;
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        ArrayList<String> errors = new ArrayList<>();
        errors.addAll(left.checkSemantics(env));
        errors.addAll(right.checkSemantics(env));
        return errors;
    }

    @Override
    public String toEquation(EnvVar e) {
        return null;
    }
}
