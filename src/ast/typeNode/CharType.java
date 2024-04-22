package ast.typeNode;

import ast.Node;
import utilities.Environment;

import java.util.ArrayList;
import java.util.HashMap;

public class CharType  extends AnyType implements TypeNode{

    @Override
    public TypeNode getType() {
        return this;
    }


    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        return null;
    }

    @Override
    public String codeGeneration(HashMap<Node, Integer> offset_idx) {
        return "i32";
    }
}
