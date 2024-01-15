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
    public EnvVar checkVarEQ(EnvVar e) {
        return e;
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
        return String.valueOf(val);
    }

    @Override
    public String codeGeneration() {
        return "i32.const "+val+"\n";
    }
}
