package ast;

import ast.typeNode.TypeNode;
import org.antlr.v4.runtime.misc.Pair;
import utilities.EnvVar;
import utilities.Environment;

import java.util.ArrayList;

public class StructNode implements Node {
    IdNode id;
    ArrayList<Pair<IdNode,TypeNode>> params;

    public StructNode(IdNode id, ArrayList<Pair<IdNode, TypeNode>> params) {
        this.id = id;
        this.params = params;
    }

    @Override
    public String toPrint(String indent) {
        return null;
    }

    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        return null;
    }

    public ArrayList<Pair<IdNode, TypeNode>> getParams() {
        return params;
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        ArrayList<String> errors = new ArrayList<>();
        if(env.checkHeadDeclaration(id.getId())){
            env.addDeclaration(id.getId(),this);
        }else{
            errors.add("Struct "+id.getId()+" is already declared");
        }
        return errors;
    }

    @Override
    public String toEquation(EnvVar e) {
        return null;
    }
}
