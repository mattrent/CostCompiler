package ast;

import ast.typeNode.TypeNode;
import utilities.EnvVar;
import utilities.Environment;

import java.util.ArrayList;

public class ReturnTypeNode implements Node{
    TypeNode type;
    IdNode id;
    public ReturnTypeNode(TypeNode type) {
        this.type = type;
        id = null;
    }

    public ReturnTypeNode(IdNode id) {
        this.id = id;
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

        if(id != null){
            if(!env.containsDeclaration(id.getId())){
                error.add(id.getId() +" is not declared");
            }
        }
        return error;
    }

    @Override
    public String toEquation(EnvVar e) {
        return null;
    }
}
