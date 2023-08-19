package ast;

import typeNode.TypeNode;

public class ReturnTypeNode implements Node{
    TypeNode type;
    IdNode id;
    public ReturnTypeNode(TypeNode type) {
        this.type = type;
        id = null;
    }

    public ReturnTypeNode(IdNode id) {
        this.id = id;
    }

    @Override
    public String toPrint(String indent) {
        return null;
    }
}
