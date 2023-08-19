package ast;

import typeNode.TypeNode;

public class FunDeclarationNode implements Node{

    private final TypeNode type;
    private final IdNode id;
    private final FormalParams formalParams;
    private final StmNode stm;
    public FunDeclarationNode(TypeNode type, IdNode id, FormalParams formalParams, StmNode stm) {
        this.type = type;
        this.id = id;
        this.formalParams = formalParams;
        this.stm = stm;
    }
    @Override
    public String toPrint(String indent) {
        return null;
    }
}
