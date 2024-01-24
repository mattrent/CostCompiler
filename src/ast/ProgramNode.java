package ast;

import ast.statement.FunDeclarationNode;
import ast.typeNode.VoidType;
import utilities.EnvVar;
import utilities.Environment;
import utilities.TypeErrorException;

import java.util.ArrayList;

public class ProgramNode implements Node {
    ArrayList<Node> complexType;
    ArrayList<Node> decServices;
    ArrayList<Node> funDec;
    Node main;

    public ProgramNode(ArrayList<Node> complexType, ArrayList<Node> decServices, ArrayList<Node> funDec, Node main) {
        this.complexType = complexType;
        this.decServices = decServices;
        this.funDec = funDec;
        this.main = main;
    }

    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        e.add(this,"main");


        if(decServices != null)
            for(Node n : decServices){
                n.checkVarEQ(e);
            }
        if(complexType != null)
            for(Node n : complexType){
                n.checkVarEQ(e);
            }
        return e;
    }

    @Override
    public Node typeCheck(Environment e) throws TypeErrorException {
        e.openScope();
        Node lastType = new VoidType();
        if(complexType != null)
            for(Node n : complexType){
                n.typeCheck(e);
            }
        if(decServices != null){
            for(Node n : decServices){
                n.typeCheck(e);
            }
        }
        if(funDec != null){
            for(Node n : funDec){
                lastType = n.typeCheck(e);
            }
        }

        main.typeCheck(e);
        e.closeScope();
        return lastType;
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
        main.checkSemantics(env);
        if(funDec != null) {
            for (Node n : funDec) {
                errors.addAll(n.checkSemantics(env));
            }
        }
        env.closeScope();
        return errors;
    }

    public String toEquation(EnvVar e){

        for (Node n : decServices){
            n.checkVarEQ(e);
        }
        StringBuilder equ = new StringBuilder();

        for(Node n : funDec){
            equ.append(n.toEquation(e));
        }
        return main.toEquation(e) + equ;




    }

    @Override
    public String codeGeneration() {
        StringBuilder codeGen = new StringBuilder();
        for (Node n : complexType){
            codeGen.append(n.codeGeneration());
        }
        for (Node n : decServices){
            codeGen.append(n.codeGeneration());
        }
        for(Node n : funDec){
            codeGen.append(n.codeGeneration());
        }
        return "(module\n"+codeGen + main.codeGeneration()+")";
    }
}
