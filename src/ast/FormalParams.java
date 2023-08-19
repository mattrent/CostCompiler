package ast;

import org.antlr.v4.runtime.misc.Pair;
import typeNode.TypeNode;

import java.util.ArrayList;

public class FormalParams implements Node {
    ArrayList<Pair<String, TypeNode>> formalParams;

    public FormalParams(ArrayList<Pair<String, TypeNode>> formalParams) {
        this.formalParams.addAll( formalParams);
    }

    @Override
    public String toPrint(String indent) {
        return null;
    }

}
