package exp;

import ast.Environment;
import ast.Node;

public class ValExpNode implements Node {
    private int val;

    public ValExpNode(int val) {
        this.val = val;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "ValExp: " + val + "\n";
    }

    @Override
    public Environment checkSemantics(Environment e) {
        return null;
    }

    @Override
    public String toEquation(Environment e) {
        return null;
    }
}
