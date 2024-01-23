package ast.typeNode;

import ast.IdNode;
import ast.Node;
import org.antlr.v4.runtime.misc.Pair;
import utilities.Environment;

import java.util.ArrayList;

public class StructType extends AnyType{
    String id;
    ArrayList<Pair<IdNode,TypeNode>> params;

    public StructType(String id, ArrayList<Pair<IdNode, TypeNode>> params) {
        this.id = id;
        this.params = params;
    }

    public ArrayList<Pair<IdNode, TypeNode>> getParams() {
        return params;
    }

    public TypeNode getType(IdNode id){
        for(Pair<IdNode,TypeNode> p : params){
            if(p.a.getId().equals(id.getId())){
                return p.b;
            }
        }
        return null;
    }
    @Override
    public Node typeCheck(Environment e) {
        return this;
    }

    @Override
    public String codeGeneration() {
        StringBuilder str = new StringBuilder();
        for(Pair<IdNode,TypeNode> p : params){
            if (p.b instanceof ArrayType)
                    str.append("(global.store " + "$").append(id).append("_").append(p.a.getId()).append(" (i32.const 0))\n");
            else
                str.append("(global.store $").append(id).append("_").append(p.a.getId()).append(" (i32.const 0)\n");
        }
        return str.toString();
    }

}
