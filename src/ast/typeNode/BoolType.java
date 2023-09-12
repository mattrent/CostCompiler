package ast.typeNode;

import ast.Node;
import utilities.Environment;

import java.util.ArrayList;

public class BoolType  extends AnyType{
    @Override
    public String toPrint(String indent) {
        return "Bool Type";
    }

    @Override
    public TypeNode getType() {
        return this;
    }

    @Override
    public Node typeCheck(Environment e) {
        return this;
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        return null;
    }
}
