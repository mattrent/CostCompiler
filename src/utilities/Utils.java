package utilities;

import ast.IdNode;
import ast.Node;
import gen.HLCostLanParser;
import typeNode.*;
import typeNode.TypeNode;

import java.util.Random;
import java.util.Set;

public class Utils{
    public static TypeNode castType(HLCostLanParser.TypeContext type) {
        if (type.getText().equals("int")) {
            return new IntType();
        } else if (type.getText().equals("bool")) {
            return new BoolType();
        } else if (type.getText().equals("string")) {
            return new StringType();
        } else if (type.getText().equals("float")) {
            return new FloatType();
        } else if (type.getText().equals("void")) {
            return new VoidType();
        } else if (type.getText().equals("char")) {
            return new CharType();
        } else if (type.getText().equals("any")) {
            return new AnyType();
        } else if (type.getText().equals("bool")) {
            return new BoolType();
        } else if (type.getText().equals("string")) {
            return new StringType();
        }
        return null;
    }

    public static char randomChar(){
        String charList = "abcdefghijklmnopqrstuvwxyz";

        Random random = new Random();
        int randomIndex = random.nextInt(charList.length());

        char randomChar = charList.charAt(randomIndex);
        return randomChar;
    }


}