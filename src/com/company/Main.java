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

public class Main {

    public static void main(String[] args) throws Exception{
        String file = "src/Example/Listing6";
        CostCompiler(file);
    }
    public static Results CostCompiler(String file) throws IOException {
        CharStream input = CharStreams.fromFileName(file);

        HLCostLanLexer lexer = new HLCostLanLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HLCostLanParser parser = new HLCostLanParser(tokens);

        SyntaxErrorListener errorListener = new SyntaxErrorListener();
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);

        if (errorListener.getSyntaxErrors().isEmpty()) {

            HLCostLanBaseVisitorImpl visitor = new HLCostLanBaseVisitorImpl();
            Node ast = visitor.visit(parser.main());

            //System.out.println(ast.toPrint(""));
            //String equ = ast.toEquation(new EnvVar());
            //System.out.println(equ);

            Environment env = new Environment();
            ArrayList<String> errorSemantics = ast.checkSemantics(env);
            if(!errorSemantics.isEmpty()){
                System.err.println(errorSemantics);
                return Results.SEMANTIC_ERROR;
            }else{
                System.out.println(ast.typeCheck(env));
                return Results.PASS;
            }
        }
            return Results.SYNTAX_ERROR;
    }
}
