package ast;

import jdk.jshell.execution.Util;
import utilities.EnvVar;
import utilities.Environment;
import utilities.TypeErrorException;
import utilities.Utils;

import java.util.ArrayList;

public class AssignmentNode implements Node {
//Can be used to AssignmentNode and StructAssignment
    IdNode id;
    Node ass;

    public AssignmentNode(IdNode id,Node ass){
        this.ass = ass;
        this.id = id;
    }


    public IdNode getId() {
        return id;
    }


    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        return null;
    }

    @Override
    public Node typeCheck(Environment e) throws TypeErrorException {
        Node type = ass.typeCheck(e);
        if(e.containsDeclaration(id.getId())){
            if(Utils.isSubtype(e.getDeclaration(id.getId()).typeCheck(e),type))
                return type;
            else
                throw new TypeErrorException("Incompatible type in assignment Node");
        }else{
            e.addDeclaration(id.getId(),type);
        }
        return type;
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        ArrayList<String> error = new ArrayList<>();
        if(env.checkHeadDeclaration(id.getId()))
            error.add(id.getId()+"is already declared");
        else
            env.addDeclaration(id.getId(),ass);
        return error;
        }

    @Override
    public String toEquation(EnvVar e) {
        if(ass instanceof CallNode || ass instanceof ForNode || ass instanceof IfNode)
            return ass.toEquation(e);
        else return " ";
    }
}
