package com.company;

import org.antlr.v4.runtime.*;
import utilities.SyntaxError;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SyntaxErrorListener extends BaseErrorListener {
    private final List<SyntaxError> SyntaxErrors = new ArrayList<>();
    public SyntaxErrorListener() {}

    public List<SyntaxError> getSyntaxErrors() {
        return this.SyntaxErrors;
    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine, String msg, RecognitionException e) {
        super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);
        this.SyntaxErrors.add(new SyntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e));
        try {
            int nLexical = 0;
            int nSyntax = 0;
            PrintWriter out = new PrintWriter("Errors.txt");
            Iterator errors = this.getSyntaxErrors().iterator();
            StringBuilder lexicalErrors = new StringBuilder("--------LEXICAL ERRORS----------\n");
            StringBuilder syntaxErrors = new StringBuilder("--------SYNTAX ERRORS----------\n");

            while(errors.hasNext()) {
                SyntaxError i = (SyntaxError) errors.next();
                if (i.getRecognizer().getClass().getSimpleName().equals("AssetLanLexer")){
                    lexicalErrors.append("Error: ").append(i.getMsg()).append(" at line ").append(i.getLine()).append("\n");
                    nLexical++;
                }else {
                    syntaxErrors.append("Error: ").append(i.getMsg()).append(" at line ").append(i.getLine()).append("\n");
                    nSyntax++;
                }
            }
            //out.println(lexicalErrors + "\n" + syntaxErrors);
            System.out.println((nLexical>0? lexicalErrors:" ") + "\n" +(nSyntax>0? syntaxErrors : " ") );
            //out.close();
        } catch (FileNotFoundException var10) {
            e.printStackTrace();
        }
    }
}