package ast;

import utilities.EnvVar;
import utilities.Environment;
import utilities.TypeErrorException;
import utilities.Utils;

import java.util.ArrayList;

public class LetAssignmentNode implements Node {
//Can be used to AssignmentNode and StructAssignment
    IdNode type;
    IdNode id;
    Node ass;

    public LetAssignmentNode(IdNode type, IdNode id, Node ass){
        this.ass = ass;
        this.id = id;
        this.type= type;
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
        if(!e.containsDeclaration(id.getId())){
            e.addDeclaration(id.getId(),type);
            if(Utils.isSubtype(e.getDeclaration(id.getId()).typeCheck(e),type))
                return type;
            else
                throw new TypeErrorException("Incompatible type in assignment Node");
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

        if(type != null && !env.containsDeclaration(type.getId())){
            error.add(type.getId()+" is not defined");
        }
        error.addAll(ass.checkSemantics(env));
        return error;
        }

    @Override
    public String toEquation(EnvVar e) {
        if(ass instanceof CallNode || ass instanceof ForNode || ass instanceof IfNode)
            return ass.toEquation(e);
        else return " ";
    }
}
