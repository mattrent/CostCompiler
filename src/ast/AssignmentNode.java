package ast;

public class AssignmentNode implements Node {
//Can be used to AssignmentNode and StructAssignment
    IdNode id;
    Node ass;

    public AssignmentNode(IdNode id,Node ass){
        this.ass = ass;
        this.id = id;
    }


    public IdNode getId() {
        return id;
    }


    @Override
    public String toPrint(String indent) {
        return null;
    }

    @Override
    public Environment checkSemantics(Environment e) {
        return null;
    }

    @Override
    public String toEquation(Environment e) {
        return null;
    }
}
