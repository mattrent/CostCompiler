package ast;

import org.antlr.v4.runtime.misc.Pair;
import ast.typeNode.TypeNode;
import utilities.EnvVar;
import utilities.Environment;

import java.util.ArrayList;

public class FormalParams implements Node {
    ArrayList<Pair<String, TypeNode>> formalParams;

    public FormalParams(ArrayList<Pair<String, TypeNode>> formalParams) {
        this.formalParams= new ArrayList<Pair<String, TypeNode>>(formalParams);
    }

    @Override
    public String toPrint(String indent) {
        return null;
    }

    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        return null;
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        ArrayList<String> error = new ArrayList<>();

        for(Pair<String, TypeNode> p : formalParams){
            if(env.checkHeadDeclaration(p.a)){
                error.add(p.a +" is already declared");
            }
            else{
                env.addDeclaration(p.a, p.b);
            }
        }
        return error;
    }

    @Override
    public String toEquation(EnvVar e) {
        return "";
    }

}
