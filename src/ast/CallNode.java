package ast;

import utilities.EnvVar;
import utilities.Environment;

import java.util.ArrayList;

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

    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        return null;
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        ArrayList<String> error = new ArrayList<>();

        if(!env.containsDeclaration(id.getId())){
            error.add(id.getId() +" is not declared");
        }

        return error;

    }

    @Override
    public String toEquation(EnvVar e) {
        return null;
    }
}
