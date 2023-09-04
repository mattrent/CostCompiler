package ast;

import typeNode.TypeNode;

import java.util.Set;

public class FunDeclarationNode implements Node{

    private final Node type;
    private final IdNode id;
    private final FormalParams formalParams;
    private final Node stm;
    public FunDeclarationNode(Node type, IdNode id, FormalParams formalParams, Node stm) {
        this.type = type;
        this.id = id;
        this.formalParams = formalParams;
        this.stm = stm;
    }
    @Override
    public String toPrint(String indent) {
        return null;
    }

    @Override
    public Environment checkSemantics(Environment e) {
        e.add(this,id.getId());
        stm.checkSemantics(e);
        return e;
    }

    @Override
    public String toEquation(Environment e) {
        Set<Node> set = e.getSet();
        String pre = id.getId() + "(" ;

        String post =  ") = " + stm.toEquation(e) + ";\n";

        Set<Node> set2 = e.getSet();
        set.retainAll(set2);

        String par = "";
        for(Node n : set){
            par += e.get(n) + ",";
        }


        par = par.substring(0,par.length()-1);
        return pre + par + post;

    }
}
