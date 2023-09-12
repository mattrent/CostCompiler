package exp;

import ast.typeNode.BoolType;
import ast.typeNode.FloatType;
import ast.typeNode.IntType;
import ast.typeNode.VoidType;
import utilities.EnvVar;
import ast.Node;
import utilities.Environment;
import utilities.Utils;

import java.util.ArrayList;

public class BinExpNode implements Node {
    private Node left;
    private Node right;
    private String op;

    public BinExpNode(Node left, Node right, String op) {
        this.left = left;
        this.right = right;
        this.op = op;
    }

    @Override
    public String toPrint(String indent) {
        return indent + "BinExp: " + op + "\n" + left.toPrint(indent + "\t") + right.toPrint(indent + "\t");
    }

    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        return null;
    }

    @Override
    public Node typeCheck(Environment e) {
        switch (op){
            case "+" :
            case "-" :
                if(Utils.isSubtype(left.typeCheck(e), new IntType()) && Utils.isSubtype(right.typeCheck(e), new IntType()))
                    return new IntType();
                else {
                    System.out.println("Type error in " + op + " operation");
                    System.exit(0);
                }
            case ">" :
            case "==":
            case "!=":
            case ">=":
                if(Utils.isSubtype(left.typeCheck(e),right.typeCheck(e)))
                    return new BoolType();
                else {
                    System.out.println("Type error in " + op + " operation");
                    System.exit(0);
                }

            case "&&":
            case "/":
            case "*":
                if(Utils.isSubtype(left.typeCheck(e), new FloatType()) && Utils.isSubtype(right.typeCheck(e), new FloatType()))
                    return new BoolType();
                else {
                    System.out.println("Type error in " + op + " operation");
                    System.exit(0);

                }

            default:
                System.err.println("Type error in " + op + " operation");
                System.exit(0);
        }
        return new VoidType();
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        ArrayList<String> errors = new ArrayList<>();
        errors.addAll(left.checkSemantics(env));
        errors.addAll(right.checkSemantics(env));
        return errors;
    }

    @Override
    public String toEquation(EnvVar e) {
        return null;
    }
}
