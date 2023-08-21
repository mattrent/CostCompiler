package ast;

public class AssignmentNode implements Node {
//Can be used to AssignmentNode and StructAssignment
    IdNode id;
    Node ass;

    public AssignmentNode(IdNode id,Node ass){
        this.ass = ass;
        this.id = id;
    }


    @Override
    public String toPrint(String indent) {
        return null;
    }
}
