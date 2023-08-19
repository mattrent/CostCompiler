package typeNode;

public class FloatType  extends AnyType implements TypeNode{
    @Override
    public String toPrint(String indent) {
        return "Float Type";
    }

    @Override
    public TypeNode getType() {
        return this;
    }
}
