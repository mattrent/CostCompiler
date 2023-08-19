package exp;

import ast.IdNode;
import ast.Node;

public class DerExpNode implements Node {
    private IdNode id;

    public DerExpNode(IdNode id) {
        this.id = id;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "DerExp: " + id.toPrint(indent + "\t");
    }
}
