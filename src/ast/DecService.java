package ast;

import org.antlr.v4.runtime.misc.Pair;
import typeNode.TypeNode;

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
    public Environment checkSemantics(Environment e) {
        return null;
    }

    @Override
    public String toEquation(Environment e) {
        return "";
    }
}
