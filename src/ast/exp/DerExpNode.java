package ast.exp;

import ast.typeNode.*;
import utilities.EnvVar;
import ast.IdNode;
import ast.Node;
import utilities.Environment;
import utilities.TypeErrorException;

import java.util.ArrayList;
import java.util.HashMap;

public class DerExpNode implements Node {
    private IdNode id;
    private Node typeNode;

    public DerExpNode(String id) {
        this.id = new IdNode(id) ;
    }

    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        e.add(this, this.id.getId());
        return e;
    }

    @Override
    public Node typeCheck(Environment e) throws TypeErrorException {
        this.typeNode = e.getDeclaration(id.getId()).typeCheck(e);
        return this.typeNode;
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        ArrayList<String> error = new ArrayList<String>();
        if(!env.containsDeclaration(id.getId())){
            error.add(id.getId() +" is not declared");
        }
        return error;
    }

    @Override
    public String toEquation(EnvVar e) {
        if(e.get(this)!= null){
            return e.get(this);
        }else return "+1";
    }

    @Override
    public String codeGeneration(HashMap<Node, Integer> offset_idx) {
        if ((this.typeNode instanceof BoolType) || (this.typeNode instanceof CharType) || (this.typeNode instanceof FloatType) || (this.typeNode instanceof NullType)) {
            return String.format("(local.get $%s)\n", id.getId());
        } else {
            return String.format("(local.get $%s_ptr)\n", id.getId()) +
                   String.format("(local.get $%s_len)\n", id.getId());

        }
    }

    public String getId() {
    	return id.getId();
    }
}
