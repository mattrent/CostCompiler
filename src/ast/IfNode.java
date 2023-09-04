package ast;

public class IfNode implements Node {

    Node exp;
    Node stmT;
    Node stmF;
    int line;
    public IfNode(CallServiceNode callServiceNode, Node node, Node node1,int line) {
        this.exp = callServiceNode;
        this.stmT = node;
        this.stmF = node1;
        this.line = line;
    }
    public IfNode(Node condNode, Node node, Node node1,int line) {
        this.exp = condNode;
        this.stmT = node;
        this.stmF = node1;
        this.line = line;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "IfNode\n" + exp.toPrint(indent + "\t") + "\n" + stmT.toPrint(indent + "\t") + "\n" + stmF.toPrint(indent + "\t");
    }

    @Override
    public Environment checkSemantics(Environment e) {
        e.add(exp);
        e.add(stmT);
        e.add(stmF);
        return e;
    }

    @Override
    public String toEquation(Environment e) {
        e.add(exp);
        e.add(stmT);
        e.add(stmF);
        String dec =  "if"+line+"(" + e.get(exp)+","+ e.get(stmT)+","+e.get(stmF)+") ";
        if(exp instanceof CallServiceNode){
            return dec +"; \n"+dec + e.get(exp)+" + " +" max("+ e.get(stmT)+","+e.get(stmF)+" )";
        }else{
            return dec +"; \n" + dec + " = " +e.get(stmT)+ " ["+ e.get(exp)+" = 1] \n"+ dec +" = "+e.get(stmF)+ " ["+ e.get(exp)+" = 0] \n"+

                    stmF.toEquation(e) + "\n" + stmT.toEquation(e);

        }

        //una volta fatto le equazioni del nodo andiamo a fare quelle per i nodi sottostanti

    }
}
