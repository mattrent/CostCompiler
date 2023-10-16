package ast.exp;

import utilities.EnvVar;
import ast.IdNode;
import ast.Node;
import utilities.Environment;
import utilities.TypeErrorException;

import java.util.ArrayList;

public class DerExpNode implements Node {
    private IdNode id;

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
        return e.getDeclaration(id.getId()).typeCheck(e);
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
        }else return "1";
    }

    public String getId() {
    	return id.getId();
    }
}
