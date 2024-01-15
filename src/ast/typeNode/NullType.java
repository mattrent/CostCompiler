package ast.typeNode;

import ast.Node;
import utilities.EnvVar;
import utilities.Environment;

import java.util.ArrayList;

public class NullType implements TypeNode {

    public NullType() {
    }

    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        return null;
    }

    @Override
    public Node typeCheck(Environment e) {
        return this;
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        return null;
    }

    @Override
    public String toEquation(EnvVar e) {
        return null;
    }

    @Override
    public String codeGeneration() {
        return null;
    }

    @Override
    public TypeNode getType() {
        return this;
    }
}
