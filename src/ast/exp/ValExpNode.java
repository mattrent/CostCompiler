package ast.exp;

import ast.typeNode.IntType;
import utilities.EnvVar;
import ast.Node;
import utilities.Environment;

import java.util.ArrayList;

public class ValExpNode implements Node {
    private int val;

    public ValExpNode(String val) {
        this.val = Integer.parseInt(val);
    }

    @Override
    public String toPrint(String indent) {
        return indent + "ValExp: " + val + "\n";
    }

    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        return null;
    }

    @Override
    public Node typeCheck(Environment e) {
        return new IntType();
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        return new ArrayList<String>();
    }

    @Override
    public String toEquation(EnvVar e) {
        return null;
    }
}
