package ast;

import java.util.Set;


public class ForNode implements Node {
    String id;
    Node exp;
    Node stm;

    int line;
    public ForNode(String id, Node exp, Node stm,int line) {
        this.id = id;
        this.exp = exp;
        this.stm = stm;
        this.line = line;
    }

    @Override
    public String toPrint(String indent) {
        return null;
    }

    @Override
    public Environment checkSemantics(Environment e) {
        //e.add(this,"for"+line);
        e = exp.checkSemantics(e);
        e = stm.checkSemantics(e);
        return e;
    }

    @Override
    public String toEquation(Environment e) {

        Environment e2 = new Environment();
        e2 = exp.checkSemantics(e2);
        Set<Node> expList = e2.getSet();
        Environment e1 = new Environment();
        e1= stm.checkSemantics(e1);
        Set<Node> stmList = e1.getSet();

        String linexp = "";
        for(Node node : expList){
            linexp += e2.get(node)+",";
        }
        linexp = linexp.substring(0,linexp.length()-1);
        String linestm = "";
        for(Node node : stmList){
            linestm += e1.get(node)+",";
        }

        linestm = linestm.substring(0,linestm.length()-1);
        String s = ")";
        String decStart = "for"+line+"(0,"+linestm + ","+ linexp+") ";
        String dec = "for"+line+"("+id+","+linestm + ","+ linexp+") ";
        String stmStr = stm.toEquation(e);



        String condLoop ="["+exp.toEquation(e)+">= "+id+"]";
        String condNoLoop = "["+id+" >= "+exp.toEquation(e)+"+ 1]";

        if(stmStr.contains("\n")){
            String stmStr1 = stmStr.substring(0,stmStr.indexOf("\n"));
            String stmStr2 = stmStr.substring(stmStr.indexOf("\n")+1);

            return  decStart + "\n" + dec +" = "+ stmStr1 + " + for"+line+"("+id+"+1,"+linestm + linexp+") "+ condLoop + "\n"
                    + dec + " = 0 \t" + condNoLoop + "\n" + stmStr2;
        }

       return  decStart + "\n" + dec +" = "+ stmStr + " + for"+line+"("+id+"+1,"+linestm + linexp+") "+ condLoop + "\n"
               + dec + " = 0 \t" + condNoLoop + "\n";
    }
}
