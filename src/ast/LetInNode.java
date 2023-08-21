package ast;

import java.util.ArrayList;

public class LetInNode implements Node {

        ArrayList<AssignmentNode> listAssignment;
        ArrayList<AssignmentNode> structAssignment;

        Node statement;

    public LetInNode(ArrayList<AssignmentNode> listAssignment, ArrayList<AssignmentNode> structAssignment, Node statement) {
        this.listAssignment = listAssignment;
        this.structAssignment = structAssignment;
        this.statement = statement;
    }


    @Override
        public String toPrint(String indent) {
            return indent + "LetInNode\n" + listAssignment.toString() + "\n" + structAssignment.toString() + "\n" + statement.toPrint(indent + "\t");
        }
}
