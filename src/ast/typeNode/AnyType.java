package ast.typeNode;

import ast.Node;
import utilities.EnvVar;
import utilities.Environment;

import java.util.ArrayList;
import java.util.HashMap;

public class AnyType implements TypeNode{
    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        return null;
    }

    @Override
    public Node typeCheck(Environment e) {
        return this;
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
        // NOTE: "any" is treated like a string, so we have a pointer + length
        return "i32,i32";
    }

    @Override
    public TypeNode getType() {
        return this;
    }
}
