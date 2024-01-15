package ast.statement;

import ast.Node;
import org.antlr.v4.runtime.misc.Pair;
import ast.typeNode.TypeNode;
import utilities.EnvVar;
import utilities.Environment;

import java.util.ArrayList;

public class FormalParams implements Node {
    ArrayList<Pair<String, TypeNode>> formalParams;

    //TODO:check if TypeNode is a complex type
    public FormalParams(ArrayList<Pair<String, TypeNode>> formalParams) {
        this.formalParams= new ArrayList<Pair<String, TypeNode>>(formalParams);
    }


    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        return null;
    }

    public Pair<String,TypeNode> get(int i){
        return formalParams.get(i);
    }
    @Override
    public Node typeCheck(Environment e) {
        if(formalParams != null){
            for(Pair<String, TypeNode> p : formalParams){
                e.addDeclaration(p.a, p.b);
            }
        }
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

    @Override
    public String codeGeneration() {
        return null;
    }

    public int size(){
        return formalParams.size();
    }

    public ArrayList<Pair<String, TypeNode>> getFormalParams() {
        return formalParams;
    }
}
