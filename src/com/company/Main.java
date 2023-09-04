package com.company;

import ast.Environment;
import ast.HLCostLanBaseVisitorImpl;
import ast.Node;
import gen.HLCostLanLexer;
import gen.HLCostLanParser;
import org.antlr.v4.runtime.*;
import utilities.SyntaxError;

public class Main {

    public static void main(String[] args) throws Exception {
        String fileName = "src/Example/Listing1.lan";
    CharStream input = CharStreams.fromFileName(fileName);
    HLCostLanLexer lexer = new HLCostLanLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    HLCostLanParser parser = new HLCostLanParser(tokens);
/*
    SyntaxErrorListener errorListener = new SyntaxErrorListener();
    lexer.removeErrorListeners();
    lexer.addErrorListener(errorListener);
    parser.removeErrorListeners();
    parser.addErrorListener(errorListener);
*/
   /* Token currentToken;
    int lineNumber = 1;
    while ((currentToken = lexer.nextToken()).getType() != Token.EOF) {
        System.out.println("Token: " + currentToken.getType() + " " + currentToken.getText() + " at line " + lineNumber);

        if (currentToken.getType() == HLCostLanLexer.NEWLINE) {
            lineNumber++;
        }
    }*/

    HLCostLanBaseVisitorImpl visitor = new HLCostLanBaseVisitorImpl();
    Node ast = visitor.visit(parser.main());
    //System.out.println(ast.toPrint(""));
    String equ = ast.toEquation(new Environment());
    System.out.println(equ);

    }
}
