package ast.typeNode;

import ast.Node;
import utilities.Environment;

import java.util.ArrayList;

public class FloatType  extends IntType {
    @Override
    public String toPrint(String indent) {
        return "Float Type";
    }

    @Override
    public TypeNode getType() {
        return this;
    }


    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        return null;
    }

    @Override
    public Node typeCheck(Environment e) {
        return this;
    }
}
