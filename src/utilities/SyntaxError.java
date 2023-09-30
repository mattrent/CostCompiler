package utilities;

import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class SyntaxError {
    private final Recognizer<?, ?> recognizer;
    private final Object offendingSymbol;
    private final int line;
    private final int charPositionInLine;
    private final String msg;
    private final RecognitionException e;

    public SyntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                       int line, int charPositionInLine, String msg, RecognitionException e) {
        this.recognizer = recognizer;
        this.offendingSymbol = offendingSymbol;
        this.line = line;
        this.charPositionInLine = charPositionInLine;
        this.msg = msg;
        this.e = e;
    }

    public Recognizer getRecognizer() {
        return this.recognizer;
    }

    public String getMsg() {
        return this.msg;
    }

    public int getLine() {
        return this.line;
    }
}