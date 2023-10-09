package ast;

import ast.typeNode.VoidType;
import utilities.EnvVar;
import utilities.Environment;
import utilities.TypeErrorException;

import java.util.ArrayList;

public class LetInNode implements Node {

    ArrayList<LetAssignmentNode> listAssignment;
    ArrayList<AssignmentNodeIn> structAssignment;

    Node statement;

    public LetInNode(ArrayList<LetAssignmentNode> listAssignment, ArrayList<AssignmentNodeIn> structAssignment, Node statement) {
        this.listAssignment = listAssignment;
        this.structAssignment = structAssignment;

        if (this.listAssignment == null) {
            this.listAssignment = new ArrayList<>();
        }
        if (this.structAssignment == null) {
            this.structAssignment = new ArrayList<>();
        }

        this.statement = statement;
    }


    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        if (listAssignment != null)
            for (Node n : listAssignment) {
                n.checkVarEQ(e);
            }

        if (structAssignment != null)
            for (Node n : structAssignment) {
                n.checkVarEQ(e);
            }
        if (statement != null)
            statement.checkVarEQ(e);

        return e;
    }

    @Override
    public Node typeCheck(Environment e) throws TypeErrorException {
        for (LetAssignmentNode n : listAssignment) {
            n.typeCheck(e);
        }

        for (AssignmentNodeIn n : structAssignment) {
            n.typeCheck(e);
        }

        if (statement != null)
            statement.typeCheck(e);

        return new VoidType();
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        ArrayList<String> errors = new ArrayList<>();

        env.openScope();
        for (LetAssignmentNode n : listAssignment) {
            errors.addAll(n.checkSemantics(env));
        }

        for (AssignmentNodeIn n : structAssignment) {
            errors.addAll(n.checkSemantics(env));
        }

        if (statement != null)
            errors.addAll(statement.checkSemantics(env));
        env.closeScope();

        return errors;
    }

    @Override
    public String toEquation(EnvVar e) {
        StringBuilder pre = new StringBuilder(" ");

        for (Node n : listAssignment) {
            String s = n.toEquation(e);
            if (!s.equals(" ")) {
                pre.append(s);
                pre.append(" + ");
            }
        }
        if (listAssignment != null)
            //remove the last character
            pre.deleteCharAt(pre.length() - 1);

        for (Node n : structAssignment) {
            String s = n.toEquation(e);
            if (!s.equals(" ")) {
                pre.append(s);
                pre.append(" +");
            }
        }
        if (!structAssignment.isEmpty())
            //remove the last character
            pre.deleteCharAt(pre.length() - 1);

        if (statement != null)
            pre.append(statement.toEquation(e));
        return String.valueOf(pre);
    }
}


