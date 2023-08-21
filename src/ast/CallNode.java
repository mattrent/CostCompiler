package ast;

public class CallNode implements Node{

    IdNode id;
    Node listCount;

    public CallNode(IdNode id, Node listCount) {
        this.id = id;
        this.listCount = listCount;
    }

    @Override
    public String toPrint(String indent) {
        return null;
    }
}
