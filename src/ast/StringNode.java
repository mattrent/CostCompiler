package ast;

import ast.typeNode.StringType;
import ast.typeNode.TypeNode;
import utilities.EnvVar;
import utilities.Environment;

import java.util.ArrayList;

public class StringNode implements Node {
    String text;
    TypeNode type;
    public StringNode(String text) {
        if (text == null)
            this.text = "";
        else{
            this.text = text;
            this.type = new StringType();
        }
    }



    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        return null;
    }

    @Override
    public Node typeCheck(Environment e) {
        return new StringType();
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        return new ArrayList<>();
    }

    @Override
    public String toEquation(EnvVar e) {
        return null;
    }

    @Override
    public String codeGeneration() {
        return null;
    }
}
