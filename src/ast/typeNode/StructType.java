package ast.typeNode;

import ast.IdNode;
import ast.Node;
import org.antlr.v4.runtime.misc.Pair;
import utilities.Environment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static utilities.Utils.getMax;

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
    public String codeGeneration(HashMap<Node, Integer> map) {
        for(Pair<IdNode,TypeNode> p : params){
            if (p.b instanceof ArrayType)
                (p.b).codeGeneration(map);
            else map.put(p.a, getMax(map.values())+1);
        }
        return "";
    }




}
