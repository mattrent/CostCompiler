package ast.typeNode;

import utilities.Environment;

import java.util.ArrayList;

public class BoolType  extends AnyType implements TypeNode{
    @Override
    public String toPrint(String indent) {
        return "Bool Type";
    }

    @Override
    public TypeNode getType() {
        return this;
    }


    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        return null;
    }
}
