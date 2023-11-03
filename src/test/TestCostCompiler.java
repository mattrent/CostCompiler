package test;

import org.junit.Test;
import java.io.IOException;

import static com.company.Main.CostCompiler;
import static org.junit.Assert.assertEquals;


public class TestCostCompiler {

    @Test
    public void test1() throws IOException {
            System.out.println("Test Listing 1");
            assertEquals(CostCompiler("example/Listing1"), Results.PASS);
        }

    @Test
    public void test2() throws Exception {
            System.out.println("Test Listing 2");
            assertEquals(CostCompiler("example/Listing2"), Results.PASS);
        }

    @Test
    public void test3() throws IOException {
            System.out.println("Test Listing 3");
            assertEquals(CostCompiler("example/Listing3"), Results.PASS);
        }

    @Test
    public void test4() throws IOException {
            System.out.println("Test Listing 4");
            assertEquals(CostCompiler("example/Listing4"),Results.PASS);
        }

    @Test
    public void test5() throws IOException {
            System.out.println("Test Listing 5");
            assertEquals(CostCompiler("example/Listing5"), Results.PASS);
        }
    @Test
    public void test6() throws IOException {
            System.out.println("Test Listing 6");
            assertEquals(CostCompiler("example/Listing6"), Results.PASS);
    }
    @Test
    public void test7() throws IOException {
            System.out.println("Test Listing 7");
            assertEquals(CostCompiler("example/Listing7"), Results.PASS);
    }
    @Test
    public void test8() throws IOException {
            System.out.println("Test Listing 8");
            assertEquals(CostCompiler("example/Listing8"), Results.PASS);
    }
    @Test
    public void test9() throws IOException {
            System.out.println("Test Listing 9");
            assertEquals(CostCompiler("example/Listing9"), Results.PASS);
    }
     @Test
     public void test10() throws IOException {
             System.out.println("Test Listing 10");
             assertEquals(CostCompiler("example/Listing10"), Results.PASS);
     }
    @Test
    public void test11() throws IOException {
            System.out.println("Test Listing 11");
            assertEquals(CostCompiler("example/Listing11"), Results.PASS);
    }

    @Test
    public void test12() throws IOException {
            System.out.println("Test Listing 12");
            assertEquals(CostCompiler("example/Listing12"), Results.PASS);
    }
}