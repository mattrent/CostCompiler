package ast;

import ast.statement.DecService;
import ast.typeNode.ArrayType;
import ast.typeNode.StructType;
import ast.typeNode.VoidType;
import utilities.EnvVar;
import utilities.Environment;
import utilities.TypeErrorException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static utilities.Utils.getMax;

public class ProgramNode implements Node {
    ArrayList<Node> complexType;
    ArrayList<Node> decServices;
    ArrayList<Node> funDec;
    Node main;

    HashMap<String, Node> complex_type = new HashMap<>();
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
        complex_type = env.clone().getSymTable(0);
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
    public String codeGeneration(HashMap<Node, Integer> offset_idx) {
        StringBuilder codeGen = new StringBuilder();
        codeGen.append("\n(import \"fl_imps\" \"__get_input_data\" (func $__get_input_data (param i32)))\n");
        codeGen.append("\n(import \"fl_imps\" \"__insert_response\" (func $__insert_response (param i32 i32)))\n");
        codeGen.append("\n(import \"fl_imps\" \"__http_request\" (func $__http_request (param i32 i32 i32 i32 i32 i32 i32 i32 i32 i32)))\n");
        codeGen.append("\n(memory 2)\n(data (i32.const 0)\n");
        for (Node n : complexType){
            n.codeGeneration(offset_idx);
        }
        //Per accedere alla posizione dedicata in memoria, se ArrayType ,
        // il dato inizia é occupato in |offset - len|...|offset|
        // se StructType, il dato inizia in |offset - len|...|offset|

        for(int i = 1; i < getMax(offset_idx.values())+1; i++){
            String value = (i < 10) ? ('0' + String.valueOf(i)) :  String.valueOf(i);
            codeGen.append("\"\\").append(value ).append("\\00\\00\\00\"\n");
        }
        codeGen.append(")\n");

        codeGen.append("\n(global $__first_available_ptr (mut i32) (i32.const 0))\n");

        for(Node n : funDec){
            codeGen.append(n.codeGeneration(offset_idx));
        }
        StringBuilder mainFun = new StringBuilder(main.codeGeneration(offset_idx));
        mainFun.insert(11, " (export \"__invoke_miniSL\")");
        for (int i = 0; i < decServices.size(); i++) {
            DecService n = (DecService) decServices.get(i);
            codeGen.append(n.codeGeneration(offset_idx, i));
        }

        String exportMemory = "\n(export \"memory\" (memory 0))\n";

        return "(module\n"+codeGen + mainFun + exportMemory + ")";
    }

}


