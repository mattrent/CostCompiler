package ast;

import ast.typeNode.IntType;
import ast.typeNode.VoidType;
import utilities.EnvVar;
import utilities.Environment;
import utilities.TypeErrorException;

import java.util.ArrayList;
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
    public EnvVar checkVarEQ(EnvVar e) {
        //e.add(this,"for"+line);
        e = exp.checkVarEQ(e);
        e = stm.checkVarEQ(e);
        return e;
    }

    @Override
    public Node typeCheck(Environment e) throws TypeErrorException {
        e.addDeclaration(id,exp.typeCheck(e));
        e.openScope();
        stm.typeCheck(e);
        e.closeScope();
        return new VoidType();
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        ArrayList<String> errors = new ArrayList<>();
        env.openScope();
        env.addDeclaration(id,new IntType());
        errors.addAll(exp.checkSemantics(env));
        errors.addAll(stm.checkSemantics(env));
        env.closeScope();
        return errors;
    }

    @Override
    public String toEquation(EnvVar e) {

        EnvVar e2 = new EnvVar();
        e2 = exp.checkVarEQ(e2);
        Set<Node> expList = e2.getSet();
        EnvVar e1 = new EnvVar();
        e1= stm.checkVarEQ(e1);
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
