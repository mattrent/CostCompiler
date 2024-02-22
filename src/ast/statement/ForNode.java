package ast.statement;

import ast.Node;
import ast.typeNode.IntType;
import ast.typeNode.VoidType;
import utilities.EnvVar;
import utilities.Environment;
import utilities.TypeErrorException;

import java.util.ArrayList;
import java.util.HashMap;
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
        if(!env.checkHeadDeclaration(id))
            env.addDeclaration(id,new IntType());
        else
            errors.add("Error: Variable "+id+" already declared");

        errors.addAll(exp.checkSemantics(env));
        errors.addAll(stm.checkSemantics(env));
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

        String linexp = " ";
        for(Node node : expList){
            linexp += e.get(node)+",";
        }
        linexp = linexp.substring(0,linexp.length()-1);
        String linestm = " ";
        for(Node node : stmList){
            linestm += e.get(node)+",";
        }
        linestm = linestm.substring(0,linestm.length()-1);
        String s = ")";
        String s_par = "," + linestm + ((!expList.isEmpty()) ? ("," + linexp) : "");
        s_par =  s_par.equals(",")? "" : s_par ;
        String decStart = "0,[for" + line + "(0" + s_par + ")],[] ";

        String dec = "for"+line+"("+id.toUpperCase()+ s_par +") ";
        String stmStr = stm.toEquation(e);

        String condLoop ="["+exp.toEquation(e)+">= "+id.toUpperCase()+"]";
        String condNoLoop = "["+id.toUpperCase()+" >= "+exp.toEquation(e)+"+ 1]";

        if(stmStr.contains("\n")){
            String stmStr1 = stmStr.substring(0,stmStr.indexOf("\n"));
            String stmStr2 = stmStr.substring(stmStr.indexOf("\n")+1);

            return  decStart + ").\neq(" + dec +","+ stmStr1 + " \neq("
                    + dec + ",0,[]," + condNoLoop + ").\n" + stmStr2;
        }

        if(stm instanceof CallNode) {
            return decStart + ").\neq(" + dec + "," + "0" + ",[for" + line + "(" + id.toUpperCase() + "+1" +s_par+ "),"+stmStr+"], " + condLoop + ").\neq(" + dec + ",0,[]," + condNoLoop + ").\n";
        }else{
            return  decStart + ").\neq(" + dec +","+ stmStr + ",[for"+line+"("+id.toUpperCase()+"+1"+s_par +")], "+ condLoop + ").\neq(" + dec + ",0,[]," + condNoLoop + ").\n";
        }
    }

    @Override
    public String codeGeneration(HashMap<Node, Integer> offset_idx) {
        return  "(local $"+id+" i32)\n" +
                "(local $"+id+"_max i32)\n" +
                "(local $res i32)\n" +
                exp.codeGeneration(offset_idx) +  //         Inizializza il contatore del ciclo (variabile locale)
                "(local.set $"+id+"_max)\n" +      //Inizializza il valore di partenza del contatore
                "(loop $for"+line+"\n" +       //Inizio del ciclo
                "(if (i32.lt_u (local.get $"+id+")(local.get $"+id+"_max) )\n"+ //Controlla se il contatore è minore del valore di fine
                "(then"
                + stm.codeGeneration(offset_idx)  //Esegue il corpo del ciclo
                +"(local.set $res)\n" //Prende il valore risultante
                +"(local.get $"+id+"\n)" +      //Prende il valore del contatore
                "(i32.const 1)\n" +       //Incrementa il contatore
                "(i32.add)\n" +           //Incrementa il contatore
                "(local.set $"+id+")\n" +       //Salva il valore del contatore
                "(br $for"+line +")\n)"            //Ritorna all'inizio del ciclo
                + "(else\n" +               //Se il contatore è maggiore del valore di fine
                "(local.get $"+id+"_max)\n" +   //Prende il valore di fine
                "(local.set $"+id+"))\n))\n(local.get $res)" ;       //Salva il valore di fine nel contatore
                // Fine del ciclo
    }
}
