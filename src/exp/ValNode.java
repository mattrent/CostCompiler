package exp;

import ast.Node;
import utilities.EnvVar;
import utilities.Environment;

import java.util.ArrayList;

public class ValNode implements Node {

    private int val;

    public ValNode(String text) {
        this.val = Integer.parseInt(text);
    }

    @Override
    public String toPrint(String indent) {
        return null;
    }

    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        return null;
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        return new ArrayList<>();
    }

    @Override
    public String toEquation(EnvVar e) {
        return null;
    }


}
