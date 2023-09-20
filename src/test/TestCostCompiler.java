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
import org.junit.internal.runners.statements.Fail;
import utilities.Environment;
import test.Results;
import java.io.IOException;

import static com.company.Main.CostCompiler;
import static org.junit.Assert.assertEquals;


public class TestCostCompiler {

    @Test
    public void test1() throws IOException {
            System.out.println("Test Listing 1");
            assertEquals(CostCompiler("src/Example/Listing1"), Results.PASS);
        }

    @Test
    public void test2() throws Exception {
            System.out.println("Test Listing 2");
            assertEquals(CostCompiler("src/Example/Listing2"), Results.PASS);
        }

    @Test
    public void test3() throws IOException {
            System.out.println("Test Listing 3");
            assertEquals(CostCompiler("src/Example/Listing3"), Results.PASS);
        }

    @Test
    public void test4() throws IOException {
            System.out.println("Test Listing 4");
            assertEquals(CostCompiler("src/Example/Listing4"),Results.PASS);
        }

    @Test
    public void test5() throws IOException {
            System.out.println("Test Listing 5");
            assertEquals(CostCompiler("src/Example/Listing5"), Results.PASS);
        }
        @Test
    public void test6() throws IOException {
            System.out.println("Test Listing 6");
            assertEquals(CostCompiler("src/Example/Listing6"), Results.SYNTAX_ERROR);

    }

}