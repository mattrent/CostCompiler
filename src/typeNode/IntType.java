package typeNode;

public class IntType extends AnyType implements TypeNode{
    @Override
    public String toPrint(String indent) {
        return "Int Type";
    }

    @Override
    public TypeNode getType() {
        return this;
    }
}
