package com.company;

import utilities.EnvVar;
import ast.HLCostLanBaseVisitorImpl;
import ast.Node;
import gen.HLCostLanLexer;
import gen.HLCostLanParser;
import org.antlr.v4.runtime.*;
import utilities.Environment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import test.Results;
import utilities.SyntaxError;
import utilities.TypeErrorException;

public class Main {

    public static void main(String[] args) throws Exception{
        String file = "src/Example/Listing11";
        System.out.print(CostCompiler(file));
    }
    public static Results CostCompiler(String file) throws IOException{
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
            Node ast = visitor.visit(parser.main());
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
                return Results.PASS;
            }
        }catch (Exception e) {
            e.printStackTrace();
            return Results.GENERIC_ERROR;
        }
    }
}
