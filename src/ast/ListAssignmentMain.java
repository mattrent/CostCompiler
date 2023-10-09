package ast;

import utilities.EnvVar;
import utilities.Environment;
import utilities.TypeErrorException;

import java.util.ArrayList;

public class ListAssignmentMain implements Node {
//Assegnamento struct senza let in in main
    ArrayList<AssignmentNodeIn> structAssignment;

    public ListAssignmentMain(ArrayList<AssignmentNodeIn> structAssignment) {
        this.structAssignment = structAssignment;
    }

    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        return null;
    }

    @Override
    public Node typeCheck(Environment e) throws TypeErrorException {
        for (AssignmentNodeIn elem:structAssignment ) {
            elem.typeCheck(e);
        }
        return structAssignment.get(0).typeCheck(e);
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        ArrayList<String> error = new ArrayList<>();
        for (AssignmentNodeIn elem:structAssignment ) {
            error.addAll(elem.checkSemantics(env));
        }
        return error;
    }

    @Override
    public String toEquation(EnvVar e) {
        return null;
    }
}
