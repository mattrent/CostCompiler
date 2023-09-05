package exp;

import ast.Environment;
import ast.IdNode;
import ast.Node;

public class DerExpNode implements Node {
    private IdNode id;

    public DerExpNode(String id) {
        this.id = new IdNode(id) ;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "DerExp: " + id.toPrint(indent + "\t");
    }

    @Override
    public Environment checkSemantics(Environment e) {
        e.add(this, this.id.getId());
        return e;
    }

    @Override
    public String toEquation(Environment e) {
        return e.get(this);
    }
}
