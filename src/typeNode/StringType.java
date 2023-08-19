package typeNode;

public class StringType  extends AnyType implements TypeNode{
    @Override
    public String toPrint(String indent) {
        return "String Type";
    }

    @Override
    public TypeNode getType() {
        return this;
    }
}
