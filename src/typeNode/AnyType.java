package typeNode;

import ast.Environment;

public class AnyType implements TypeNode{
    @Override
    public String toPrint(String indent) {
        return "Any Type";
    }

    @Override
    public Environment checkSemantics(Environment e) {
        return null;
    }

    @Override
    public String toEquation(Environment e) {
        return null;
    }

    @Override
    public TypeNode getType() {
        return this;
    }
}
