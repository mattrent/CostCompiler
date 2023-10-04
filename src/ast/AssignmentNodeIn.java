package ast;

import ast.typeNode.StructType;
import ast.typeNode.TypeNode;
import org.antlr.v4.runtime.misc.Pair;
import utilities.EnvVar;
import utilities.Environment;
import utilities.TypeErrorException;
import utilities.Utils;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.Objects;

public class AssignmentNodeIn implements Node{
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
        if(!Utils.isSubtype(assType.typeCheck(e),st.getType(id).typeCheck(e)))
            throw new TypeErrorException(id +" must be the same type of"+ type);
        else return assType.typeCheck(e);
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
            return ass.toEquation(e);
        else return " ";
    }

}
