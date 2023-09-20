package ast.typeNode;

import ast.Node;
import utilities.EnvVar;
import utilities.Environment;

import java.util.ArrayList;

public class IdType implements TypeNode{

    String id;

    public IdType(String id) {
        this.id = id;
    }

    public IdType(String id, IdType idType) {
        this.id = id;
    }
    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        return null;
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        return null;
    }

    @Override
    public String toEquation(EnvVar e) {
        return null;
    }

    @Override
    public Node typeCheck(Environment e) {
        if(e.containsDeclaration(id)){
            return e.getDeclaration(id).typeCheck(e);
        }
        return null;
    }

    @Override
    public TypeNode getType() {
        return null;
    }
}
