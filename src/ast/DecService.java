package ast;

import org.antlr.v4.runtime.misc.Pair;
import ast.typeNode.TypeNode;
import utilities.EnvVar;
import utilities.Environment;

import java.util.ArrayList;

public class DecService implements Node{
    IdNode id;
    ArrayList<Pair<IdNode,TypeNode>> params;
    ReturnTypeNode returnType;

    public DecService(IdNode id, ArrayList<Pair<IdNode, TypeNode>> params, ReturnTypeNode returnType) {
        this.id = id;
        this.params = params;
        this.returnType = returnType;
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
        env.addDeclaration(id.getId(),this);
        return new ArrayList<>();
    }

    @Override
    public String toEquation(EnvVar e) {
        return "";
    }
}
