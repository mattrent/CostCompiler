package ast.exp;

import ast.typeNode.BoolType;
import ast.typeNode.FloatType;
import ast.typeNode.IntType;
import ast.typeNode.VoidType;
import utilities.EnvVar;
import ast.Node;
import utilities.Environment;
import utilities.TypeErrorException;
import utilities.Utils;

import java.util.ArrayList;
import java.util.Objects;

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
    public EnvVar checkVarEQ(EnvVar e) {
        e = left.checkVarEQ(e);
        e = right.checkVarEQ(e);
        return e;
    }

    @Override
    public Node typeCheck(Environment e) throws TypeErrorException {
        switch (op){
            case "+" :
            case "-" :
                if(Utils.isSubtype(left.typeCheck(e), new IntType()) && Utils.isSubtype(right.typeCheck(e), new IntType()))
                    return new IntType();
                else {
                    throw new TypeErrorException("Type error in " + op + " operation");
                }
            case ">" :
            case "==":
            case "!=":
            case "&&":
            case ">=":
                if(Utils.isSubtype(left.typeCheck(e),right.typeCheck(e)))
                    return new BoolType();
                else {
                    throw new TypeErrorException("Type error in " + op + " operation");
                }

            case "/":
            case "*":
                if(Utils.isSubtype(left.typeCheck(e), new FloatType()) && Utils.isSubtype(right.typeCheck(e), new FloatType()))
                    return new FloatType();
                else {
                    throw new TypeErrorException("Type error in " + op + " operation");
                }
            default:
                throw new TypeErrorException("Type error in " + op + " operation");
        }
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
        return left.toEquation(e) + (Objects.equals(op, "==") ? "=" : op) + right.toEquation(e);
    }

    @Override
    public String codeGeneration() {
        String code = "";
        switch (op){
            case "+" :
                code += "i32.add\n";
                break;
            case "-" :
                code += "i32.sub\n";
                break;
            case ">" :
                code += "i32.gt_s\n";
                break;
            case "==":
                code += "i32.eq\n";
                break;
            case "!=":
                code += "i32.ne\n";
                break;
            case "&&":
                code += "i32.and\n";
                break;
            case ">=":
                code += "i32.ge_s\n";
                break;
            case "/":
                code += "f32.div\n";
                break;
            case "*":
                code += "i32.mul\n";
                break;
        }
        return "("+code+ right.codeGeneration() + left.codeGeneration()+")\n";
    }

    public String getEquation(EnvVar e) {
        if(e.get(this)== null){
            if(e.get(left) != null)
                return e.get(left);
            else if(e.get(right) != null)
                return e.get(right);
            else
                return left.toEquation(e) + (Objects.equals(op, "==") ? "=" : op) + right.toEquation(e);
        }
        return null;
    }
    public String negToEquation(EnvVar e) {
        switch (op) {
            case "+":
                return left.toEquation(e)+"-" + right.toEquation(e);
            case "-":
                return left.toEquation(e)+ "+" + right.toEquation(e);
            case "*":
                return left.toEquation(e)+ "/" + right.toEquation(e);
            case "/":
                return left.toEquation(e)+ "*" + right.toEquation(e);
            case ">":
                return left.toEquation(e)+ "<=" + right.toEquation(e);
            case "<":
                return left.toEquation(e)+ ">=" + right.toEquation(e);
            case ">=":
                return left.toEquation(e)+ "<" + right.toEquation(e);
            case "<=":
                return left.toEquation(e)+ ">" + right.toEquation(e);
            case "==":
                return left.toEquation(e)+ "!=" + right.toEquation(e);
            case "!=":
                return left.toEquation(e)+ "==" + right.toEquation(e);
        }
        return null;
    }
}
