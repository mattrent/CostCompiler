package ast.typeNode;

import utilities.Environment;

import java.util.ArrayList;

public class CharType  extends AnyType implements TypeNode{

    @Override
    public TypeNode getType() {
        return this;
    }


    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        return null;
    }
}
