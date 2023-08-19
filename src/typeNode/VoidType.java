package typeNode;

public class VoidType  extends AnyType implements TypeNode{
    @Override
    public String toPrint(String indent) {
        return "Void Type";
    }

    @Override
    public TypeNode getType() {
        return this;
    }
}
