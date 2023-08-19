package typeNode;

public class BoolType  extends AnyType implements TypeNode{
    @Override
    public String toPrint(String indent) {
        return "Bool Type";
    }

    @Override
    public TypeNode getType() {
        return this;
    }
}
