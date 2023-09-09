package ast.typeNode;

import utilities.EnvVar;
import utilities.Environment;

import java.util.ArrayList;

public class ArrayType implements TypeNode {
    TypeNode type;

    public ArrayType(TypeNode type) {
        this.type = type;

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
        return null;
    }

    @Override
    public String toEquation(EnvVar e) {
        return null;
    }

    @Override
    public TypeNode getType() {
        return this;
    }
}
