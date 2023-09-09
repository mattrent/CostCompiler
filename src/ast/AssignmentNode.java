package ast;

import utilities.EnvVar;
import utilities.Environment;

import java.util.ArrayList;

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
    public EnvVar checkVarEQ(EnvVar e) {
        return null;
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        ArrayList<String> error = new ArrayList<>();
        if(env.checkHeadDeclaration(id.getId()))
            error.add(id.getId()+"is already declared");
        else
            env.addDeclaration(id.getId(),ass);
        return error;
        }

    @Override
    public String toEquation(EnvVar e) {
        return null;
    }
}
