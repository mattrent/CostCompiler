package com.company;

import utilities.EnvVar;
import ast.HLCostLanBaseVisitorImpl;
import ast.Node;
import gen.HLCostLanLexer;
import gen.HLCostLanParser;
import org.antlr.v4.runtime.*;
import utilities.Environment;

public class Main {

    public static void main(String[] args) throws Exception {
        String fileName = "src/Example/Listing1.lan";
        CharStream input = CharStreams.fromFileName(fileName);
        HLCostLanLexer lexer = new HLCostLanLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        HLCostLanParser parser = new HLCostLanParser(tokens);

        HLCostLanBaseVisitorImpl visitor = new HLCostLanBaseVisitorImpl();
        Node ast = visitor.visit(parser.main());

        SyntaxErrorListener errorListener = new SyntaxErrorListener();
        lexer.removeErrorListeners();
        lexer.addErrorListener(errorListener);
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);
        if (errorListener.getSyntaxErrors().isEmpty()) {

            //System.out.println(ast.toPrint(""));
            //String equ = ast.toEquation(new EnvVar());
            //System.out.println(equ);

            Environment env = new Environment();
            System.out.println(ast.checkSemantics(env));
        }
    }
}
