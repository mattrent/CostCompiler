package ast;

import utilities.EnvVar;
import utilities.Environment;

import java.util.ArrayList;

public class MainProgramNode implements Node {
    ArrayList<Node> complexType;
    ArrayList<Node> decServices;
    ArrayList<Node> funDec;
    public MainProgramNode(ArrayList<Node> complexType, ArrayList<Node> decServices, ArrayList<Node> funDec) {
        this.complexType = complexType;
        this.decServices = decServices;
        this.funDec = funDec;
    }

    @Override
    public String toPrint(String indent) {
        return null;
    }

    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        e.add(this,"main");


        for(Node n : decServices){
            n.checkVarEQ(e);
        }
        for(Node n : funDec){
            n.checkVarEQ(e);
        }
        return e;
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        env.openScope();
        ArrayList<String> errors = new ArrayList<>();
        if(complexType != null) {
            for (Node n : complexType) {
                errors.addAll(n.checkSemantics(env));
            }
        }
        if(decServices != null) {
            for (Node n : decServices) {
                errors.addAll(n.checkSemantics(env));
            }
        }
        if(funDec != null) {
            for (Node n : funDec) {
                errors.addAll(n.checkSemantics(env));
            }
        }
        env.closeScope();
        return errors;
    }

    public String toEquation(EnvVar e){

        String equ = "";
        for(Node n : funDec){
            equ += n.toEquation(e);
        }
        return equ;




    }
}
