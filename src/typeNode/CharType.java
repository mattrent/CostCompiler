package typeNode;

public class CharType  extends AnyType implements TypeNode{
    @Override
    public String toPrint(String indent) {
        return "Char Type";
    }

    @Override
    public TypeNode getType() {
        return this;
    }
}
