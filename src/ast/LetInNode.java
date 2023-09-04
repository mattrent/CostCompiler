package ast;

import java.util.ArrayList;

public class LetInNode implements Node {

        ArrayList<AssignmentNode> listAssignment;
        ArrayList<AssignmentNode> structAssignment;

        Node statement;

    public LetInNode(ArrayList<AssignmentNode> listAssignment, ArrayList<AssignmentNode> structAssignment, Node statement) {
        this.listAssignment = listAssignment;
        this.structAssignment = structAssignment;

        if(this.listAssignment == null){
            this.listAssignment = new ArrayList<>();
        }
        if(this.structAssignment == null){
            this.structAssignment = new ArrayList<>();
        }

        this.statement = statement;
    }


    @Override
        public String toPrint(String indent) {
            return indent + "LetInNode\n" + listAssignment.toString() + "\n" + structAssignment.toString() + "\n" + statement.toPrint(indent + "\t");
        }

    @Override
    public Environment checkSemantics(Environment e) {
        e.add(this,"letin");

        statement.checkSemantics(e);

        return e;
    }

    @Override
    public String toEquation(Environment e) {
        return null;
    }
}
