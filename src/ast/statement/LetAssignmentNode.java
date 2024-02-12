package ast.statement;

import ast.IdNode;
import ast.Node;
import ast.typeNode.TypeNode;
import utilities.EnvVar;
import utilities.Environment;
import utilities.TypeErrorException;
import utilities.Utils;

import java.util.ArrayList;
import java.util.HashMap;

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
        id.checkVarEQ(e);
        return e;
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
        if(ass instanceof CallNode || ass instanceof ForNode || ass instanceof IfNode || ass instanceof CallServiceNode)
            return ass.toEquation(e);
        else return "";
    }

    @Override
    public String codeGeneration(HashMap<Node, Integer> offset_idx) {
        return ass.codeGeneration(offset_idx)+"\nload.set $"+id.getId();
    }

    public Node getAss() {
        return this.ass;
    }
}
