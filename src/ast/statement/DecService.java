package ast.statement;

import ast.IdNode;
import ast.Node;
import org.antlr.v4.runtime.misc.Pair;
import ast.typeNode.TypeNode;
import utilities.EnvVar;
import utilities.Environment;

import java.util.ArrayList;
import java.util.HashMap;

public class DecService implements Node {
    IdNode id;
    ArrayList<Pair<IdNode,TypeNode>> params;
    ReturnTypeNode returnType;

    public DecService(IdNode id, ArrayList<Pair<IdNode, TypeNode>> params, ReturnTypeNode returnType) {
        this.id = id;
        this.params = params;
        this.returnType = returnType;
    }

    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        e.add(new CallServiceNode(id.getId(),null));
        return e;
    }

    @Override
    public Node typeCheck(Environment e) {
        e.addDeclaration(id.getId(),this);
        return this;
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        ArrayList<String> errors = new ArrayList<>();
        env.addDeclaration(id.getId(),this);
        for (Pair<IdNode, TypeNode> elem : params) {
            if(elem != null && elem.a != null){
                if(!env.containsDeclaration(elem.a.getId()))
                    errors.add(elem.a.getId() + " is not declared");
            }
        }
        return errors;
    }

    @Override
    public String toEquation(EnvVar e) {
        return "";
    }

    @Override
    public String codeGeneration(HashMap<Node, Integer> offset_idx) {
        return null;
    }

    public ArrayList<Pair<IdNode, TypeNode>> getParams() {
        return params;
    }
}
