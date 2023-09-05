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
        Environment e1 = new Environment();
        stm.checkSemantics(e1);

        String pre = id.getId() + "(" ;

        String post =  ") = " + stm.toEquation(e1) + ";\n";

        Set<Node> set = e1.getSet();
        StringBuilder par = new StringBuilder();
        for(Node n : set){
            par.append(e1.get(n)).append(",");
        }


        par = new StringBuilder(par.substring(0, par.length() - 1));
        return pre + par + post;

    }
}
