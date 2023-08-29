package com.company;

import ast.HLCostLanBaseVisitorImpl;
import ast.Node;
import gen.HLCostLanLexer;
import gen.HLCostLanParser;
import org.antlr.v4.runtime.*;
public class Main {

    public static void main(String[] args) throws Exception {
        String fileName = "src/Example/Listing1.lan";
    CharStream input = CharStreams.fromFileName(fileName);
    HLCostLanLexer lexer = new HLCostLanLexer(input);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    HLCostLanParser parser = new HLCostLanParser(tokens);

    HLCostLanParser.MainContext tree = parser.main();
    HLCostLanBaseVisitorImpl visitor = new HLCostLanBaseVisitorImpl();
    Node ast = visitor.visit(tree);
    System.out.println(ast.toPrint(""));
    }
}
