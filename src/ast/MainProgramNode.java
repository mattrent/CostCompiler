package ast;

import java.awt.*;
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
    public Environment checkSemantics(Environment e) {
        e.add(this,"main");


        for(Node n : decServices){
            n.checkSemantics(e);
        }
        for(Node n : funDec){
            n.checkSemantics(e);
        }
        return e;
    }

    public String toEquation(Environment e){

        String equ = "";
        for(Node n : funDec){
            equ += n.toEquation(e);
        }
        return equ;




    }
}
