package ast;

import utilities.EnvVar;
import utilities.Environment;

import java.util.ArrayList;

public class IdNode implements Node{
    String id;

    public IdNode(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        return null;
    }

    @Override
    public Node typeCheck(Environment e) {
        return e.getDeclaration(id).typeCheck(e);
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        return new ArrayList<>();
    }

    @Override
    public String toEquation(EnvVar e) {
        return id;
    }
}
