package utilities;

import ast.Node;
import gen.HLCostLanParser;
import ast.typeNode.*;
import ast.typeNode.TypeNode;

import java.util.Random;

public class Utils{
    public static boolean isSubtype (Node a, Node b) {
        if (a instanceof StructType && b instanceof StructType) {
            return ((StructType) a).equals(((StructType) b));
        }
        return a.getClass().isAssignableFrom( b.getClass()); // ||
        // ( (a instanceof BoolTypeNode) && (b instanceof IntTypeNode) ); //
    }

    public static TypeNode castType(HLCostLanParser.TypeContext type) {
        String typeString = type.getText().toLowerCase();
        if (typeString.equals("int")) {
            return new IntType();
        } else if (typeString.equals("bool")) {
            return new BoolType();
        } else if (typeString.equals("string")) {
            return new StringType();
        } else if (typeString.equals("float")) {
            return new FloatType();
        } else if (typeString.equals("void")) {
            return new VoidType();
        } else if (typeString.equals("char")) {
            return new CharType();
        } else if (typeString.equals("any")) {
            return new AnyType();
        }
        return new NullType();
    }

    public static String randomChar(){
        String charList = "abcdefghijklmnopqrstuvwxyz";

        Random random = new Random();
        int randomIndex = random.nextInt(charList.length());

        String randomChar = String.valueOf(charList.charAt(randomIndex));
        return randomChar;
    }


}