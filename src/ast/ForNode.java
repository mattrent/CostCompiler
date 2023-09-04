package ast;

public class ForNode implements Node {
    String id;
    Node exp;
    Node stm;

    public ForNode(String id, Node exp, Node stm) {
        this.id = id;
        this.exp = exp;
        this.stm = stm;
    }

    @Override
    public String toPrint(String indent) {
        return null;
    }

    @Override
    public Environment checkSemantics(Environment e) {
        e.add(this,id);
        stm.checkSemantics(e);
        return e;
    }

    @Override
    public String toEquation(Environment e) {
        return null;
    }
}
