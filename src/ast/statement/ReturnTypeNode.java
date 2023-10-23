package ast.statement;

import ast.IdNode;
import ast.Node;
import ast.typeNode.TypeNode;
import utilities.EnvVar;
import utilities.Environment;
import utilities.TypeErrorException;

import java.util.ArrayList;

public class ReturnTypeNode implements Node {
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
    public EnvVar checkVarEQ(EnvVar e) {
        return null;
    }

    @Override
    public Node typeCheck(Environment e) throws TypeErrorException {
        if(id != null  && e.getDeclaration(id.getId()) != null){
            return e.getDeclaration(id.getId());
        }else{
            return type.typeCheck(e);
        }
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
