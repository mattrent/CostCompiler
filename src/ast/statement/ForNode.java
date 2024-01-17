package ast.statement;

import ast.Node;
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
        String decStart = "0,[for"+line+"(0,"+linestm + ((!expList.isEmpty()) ? (","+ linexp) : "")
                                            +")],[] ";

        String dec = "for"+line+"("+id.toUpperCase()+","+linestm +
                                                    ((!expList.isEmpty()) ? (","+ linexp) : "") +") ";
        String stmStr = stm.toEquation(e);



        String condLoop ="["+exp.toEquation(e)+">= "+id.toUpperCase()+"]";
        String condNoLoop = "["+id.toUpperCase()+" >= "+exp.toEquation(e)+"+ 1]";

        if(stmStr.contains("\n")){
            String stmStr1 = stmStr.substring(0,stmStr.indexOf("\n"));
            String stmStr2 = stmStr.substring(stmStr.indexOf("\n")+1);

            return  decStart + ").\neq(" + dec +","+ stmStr1 + " \neq("
                    + dec + ",0,[]," + condNoLoop + ").\n" + stmStr2;
        }

       return  decStart + ").\neq(" + dec +","+ stmStr + ",[for"+line+"("+id.toUpperCase()+"+1,"+linestm+
               ((!expList.isEmpty()) ? (","+ linexp) : "") +")], "+ condLoop + ").\neq(" + dec + ",0,[]," + condNoLoop + ").\n";
    }

    @Override
    public String codeGeneration() {

        return  "(loop $for"+line+"\n" +
                exp.codeGeneration() +  //         Inizializza il contatore del ciclo (variabile locale)
                " i32.const $for"+line+"counter\n" +      //Inizializza il valore di partenza del contatore
                " i32.lt_s\n (if "       //Controlla se il contatore è minore del valore di fine
                + stm.codeGeneration()  //Esegue il corpo del ciclo
                +"local.get $for"+line+"counter\n" +      //Prende il valore del contatore
                "i32.const 1\n" +       //Incrementa il contatore
                "i32.add\n" +           //Incrementa il contatore
                "local.set $for"+line+"counter\n" +       //Salva il valore del contatore

                "br $for"+line +"\n)"+            //Ritorna all'inizio del ciclo
                "(then nop )\n)";
                // Fine del ciclo
    }
}
