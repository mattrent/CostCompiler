package ast.typeNode;

import ast.IdNode;
import ast.Node;
import utilities.EnvVar;
import utilities.Environment;

import java.util.ArrayList;

public class ArrayType extends AnyType {
    TypeNode type;
    IdNode id;
    int len;
    public ArrayType(TypeNode type) {
        this.type = type;
        this.id = null;
    }
    public ArrayType(TypeNode type, IdNode id,int length){
        this.type = type;
        this.id = id;
        this.len = length;
    }

    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        return null;
    }

    @Override
    public Node typeCheck(Environment e) {
        if(e.getDeclaration(id.getId()) == null)
            e.addDeclaration(id.getId(),this);
        else
            return this;
        return this;
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        if(id != null){
            env.addDeclaration(id.getId(),this);
        }

        return new ArrayList<>();
    }

    @Override
    public String toEquation(EnvVar e) {
        return null;
    }

    @Override
    public TypeNode getType() {
        return this;
    }

    @Override
    public String codeGeneration() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < len; i++) {
            str.append("(global.store $").append(id.getId()).append("[").append(i).append("] (i32.const 0))\n");
        }
        return str.toString();

    }
    public int getlen(){
        return len;
    }
}
