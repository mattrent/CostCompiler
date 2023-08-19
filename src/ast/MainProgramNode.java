package ast;

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
}
