package ast.typeNode;

import ast.Node;
import utilities.EnvVar;
import utilities.Environment;
import utilities.TypeErrorException;

import java.util.ArrayList;
import java.util.HashMap;

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
    public String codeGeneration(HashMap<Node, Integer> offset_idx) {
        return null;
    }

    @Override
    public Node typeCheck(Environment e) throws TypeErrorException {
        if(e.containsDeclaration(id)){
            return e.getDeclaration(id).typeCheck(e);
        }
        return new VoidType();
    }

    @Override
    public TypeNode getType() {
        return null;
    }
}
