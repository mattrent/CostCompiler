package typeNode;

public class AnyType implements TypeNode{
    @Override
    public String toPrint(String indent) {
        return "Any Type";
    }

    @Override
    public TypeNode getType() {
        return this;
    }
}
