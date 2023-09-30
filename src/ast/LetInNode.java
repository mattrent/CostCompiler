package ast;

import ast.typeNode.VoidType;
import utilities.EnvVar;
import utilities.Environment;
import utilities.TypeErrorException;

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
    public EnvVar checkVarEQ(EnvVar e) {
        if(statement != null)
            statement.checkVarEQ(e);

        return e;
    }

    @Override
    public Node typeCheck(Environment e) throws TypeErrorException {
        for(AssignmentNode n : listAssignment){
            n.typeCheck(e);
        }

        for(AssignmentNode n : structAssignment){
            n.typeCheck(e);
        }

        if(statement != null)
            statement.typeCheck(e);

        return new VoidType();
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        ArrayList<String> errors = new ArrayList<>();

        for(AssignmentNode n : listAssignment){
            errors.addAll(n.checkSemantics(env));
        }

        for(AssignmentNode n : structAssignment){
            errors.addAll(n.checkSemantics(env));
        }

        if(statement != null)
            errors.addAll(statement.checkSemantics(env));

        return errors;
    }

    @Override
    public String toEquation(EnvVar e) {
        StringBuilder pre = new StringBuilder(" ");

        concatEq(e, pre, listAssignment);
        concatEq(e, pre, structAssignment);

        if(statement != null)
            pre.append(statement.toEquation(e));
        return String.valueOf(pre);
    }

    private void concatEq(EnvVar e, StringBuilder pre, ArrayList<AssignmentNode> ass) {
        for(AssignmentNode n : ass){
            String s = n.toEquation(e);
            if(!s.equals(" ")){
                pre.append(s);
                pre.append(" + ");
            }
        }
        if(ass != null)
            //remove the last character
            pre.deleteCharAt(pre.length()-1);
    }
}
