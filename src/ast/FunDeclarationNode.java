package ast;

import ast.typeNode.VoidType;
import utilities.EnvVar;
import utilities.Environment;
import utilities.TypeErrorException;

import java.util.ArrayList;
import java.util.Set;

public class FunDeclarationNode implements Node{

    private final Node type;
    private final IdNode id;
    private final FormalParams formalParams;
    private final Node stm;
    public FunDeclarationNode(Node type, IdNode id, FormalParams formalParams, Node stm) {
        this.type = type;
        this.id = id;
        this.formalParams = formalParams;
        this.stm = stm;
    }

    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        e.add(this,id.getId());
        stm.checkVarEQ(e);
        return e;
    }

    @Override
    public Node typeCheck(Environment e) throws TypeErrorException {
        e.addDeclaration(id.getId(),this);
        e.openScope();
        if(formalParams != null) {
            formalParams.typeCheck(e);
        }
        stm.typeCheck(e);
        e.closeScope();
        return type.typeCheck(e);
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        ArrayList<String> errors = new ArrayList<>();
        if(env.containsDeclaration(id.getId()))
            errors.add(id.getId()+"is already declared");
        else
            env.addDeclaration(id.getId(),this);
        env.openScope();
        if(formalParams != null) {
            errors.addAll(formalParams.checkSemantics(env));
        }
        errors.addAll(stm.checkSemantics(env));
        errors.addAll(type.checkSemantics(env));
        env.closeScope();
        return errors;
    }

    @Override
    public String toEquation(EnvVar e) {
        EnvVar e1 = new EnvVar();
        stm.checkVarEQ(e1);

        String pre = id.getId() + "(" ;

        String post =  ") = " + stm.toEquation(e1) + ";\n";

        Set<Node> set = e1.getSet();
        StringBuilder par = new StringBuilder();
        for(Node n : set){
            par.append(e1.get(n)).append(",");
        }


        par = new StringBuilder(par.substring(0, par.length() - 1));
        return pre + par + post;

    }

    public FormalParams getFormalParams() {
        return formalParams;
    }

    public Node getReturnNode(){
        return type;
    }
}
