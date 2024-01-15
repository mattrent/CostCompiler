package ast.statement;

import ast.IdNode;
import ast.Node;
import ast.StructNode;
import ast.typeNode.StructType;
import utilities.EnvVar;
import utilities.Environment;
import utilities.TypeErrorException;
import utilities.Utils;

import java.util.ArrayList;

public class AssignmentNodeIn implements Node {
    IdNode type;
    IdNode id;
    Node ass;

    public AssignmentNodeIn(IdNode type, IdNode id, Node ass){
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
        StructType st = (StructType) e.getDeclaration(type.getId());
        Node assType = ass.typeCheck(e);
        if(!Utils.isSubtype(assType.typeCheck(e),st))
            throw new TypeErrorException(id +" must be the same type of"+ type);
        else {
            e.addDeclaration(id.getId(),ass.typeCheck(e));
            return assType.typeCheck(e);
        }
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
      //in this case type is must != null
        ArrayList<String> error = new ArrayList<>();
        if(env.getDeclaration(type.getId())== null)
            error.add(id.getId()+"is not declared");
        else {
            StructNode st = (StructNode) env.getDeclaration(type.getId());
            if(st.getParamsId(id)== null)
                error.add(id.getId() + " is not declated");
        }
        return error;
    }

    @Override
    public String toEquation(EnvVar e) {
        if(ass instanceof CallNode || ass instanceof ForNode || ass instanceof IfNode)
            return "0,["+ass.toEquation(e)+"],[]).";
        else if(ass instanceof StructAssignmentNode)
            return ass.toEquation(e);
        else return " ";
    }

    public String codeGeneration(){
        return null;
    }



}
