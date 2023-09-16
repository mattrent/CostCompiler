package test;
import ast.HLCostLanBaseVisitorImpl;
import ast.Node;
import com.company.Main;
import com.company.SyntaxErrorListener;
import gen.HLCostLanLexer;
import gen.HLCostLanParser;
import junit.framework.TestCase;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Test;
import utilities.Environment;

import java.io.IOException;


public class TestCostCompiler {

    void program(String fileName) throws IOException {
        CharStream input = CharStreams.fromFileName(fileName);
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
            ast.checkSemantics(env);
            ast.typeCheck(env);
        }
    }
    @Test
    public void test1() throws IOException {
            System.out.println("Test Listing 1");
            program("src/Example/Listing1");
        }

    @Test
    public void test2() throws IOException {
            System.out.println("Test Listing 2");
            program("src/Example/Listing2");
        }

    @Test
    public void test3() throws IOException {
            System.out.println("Test Listing 3");
            program("src/Example/Listing3");
        }

    @Test
    public void test4() throws IOException {
            System.out.println("Test Listing 4");
            program("src/Example/Listing4");
        }

    @Test
    public void test5() throws IOException {
            System.out.println("Test Listing 5");
            program("src/Example/Listing5");
        }


}