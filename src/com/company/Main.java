package com.company;

import utilities.EnvVar;
import ast.HLCostLanBaseVisitorImpl;
import ast.Node;
import gen.HLCostLanLexer;
import gen.HLCostLanParser;
import org.antlr.v4.runtime.*;
import utilities.Environment;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;


import test.Results;
import utilities.TypeErrorException;

import static java.lang.Thread.sleep;
public class Main {

    public static void main(String[] args) throws Exception{
        CostCompiler(args[0]);
    }

    public static Results CostCompiler(String file) throws IOException{
        //String pathToDebug = "/src/com/company"; //comments this line to run the compiler by command line
        String path = "";

        try {
            CharStream input = CharStreams.fromFileName(file);
            HLCostLanLexer lexer = new HLCostLanLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            HLCostLanParser parser = new HLCostLanParser(tokens);

            SyntaxErrorListener errorListener = new SyntaxErrorListener();
            lexer.removeErrorListeners();
            lexer.addErrorListener(errorListener);
            parser.removeErrorListeners();
            parser.addErrorListener(errorListener);

            HLCostLanBaseVisitorImpl visitor = new HLCostLanBaseVisitorImpl();
            Node ast = visitor.visit(parser.prg());
            if(!errorListener.getSyntaxErrors().isEmpty()){
                return Results.SYNTAX_ERROR;
            }
            Environment env = new Environment();
            ArrayList<String> errorSemantics = ast.checkSemantics(env);
            if (!errorSemantics.isEmpty()) {
                System.err.println(errorSemantics);
                return Results.SEMANTIC_ERROR;
            } else {
                try{
                    ast.typeCheck(new Environment());
                }catch (TypeErrorException e){
                    System.err.println(e.getMessage());
                    return Results.TYPE_CHECKING_ERROR;
                }

                String equ = ast.toEquation(new EnvVar());
                System.out.println(equ);
                BufferedWriter writer = new BufferedWriter(new java.io.FileWriter("equation.ces"));
                writer.write(equ);
                writer.close();

                String wasmCode = ast.codeGeneration();
                System.out.println(wasmCode);

                BufferedWriter writerWasm = new BufferedWriter(new java.io.FileWriter("equation.wasm"));
                writerWasm.write(wasmCode);
                writerWasm.close();

                String osName= System.getProperty("os.name");
                Process p;
                if(osName.contains("Windows"))
                    p = new ProcessBuilder("wsl", "."+path+"/pubs_static", "-file", "equation.ces").inheritIO().start();
                else
                    p= new ProcessBuilder( "."+path+"/pubs_static", "-file", "equation.ces").inheritIO().start();

                return Results.PASS;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return Results.GENERIC_ERROR;
        }
    }
}
