package ast;

import ast.typeNode.StructType;
import ast.typeNode.TypeNode;
import org.antlr.v4.runtime.misc.Pair;
import utilities.EnvVar;
import utilities.Environment;

import java.util.ArrayList;
import java.util.Objects;

public class CallFunNode implements Node{
    //CallFunNode : ID.ID;
    ArrayList<String> listId;

    public CallFunNode(ArrayList<String> listId) {
        this.listId = listId;
    }

    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        return null;
    }

    @Override
    public Node typeCheck(Environment e) {
        //Dobbiamo controllare parametri formali e attuali coincidano
        //TODO:permettere struct annidate(al momento non è possibile)
        for(String id : listId){
            if(e.containsDeclaration(id)){
                Node node = e.getDeclaration(id).typeCheck(e);
                if(node instanceof StructNode){
                    StructNode structNode = (StructNode) node;
                    id = listId.get(1);
                    for(Pair<IdNode, TypeNode> elem : structNode.getParams()){
                        if(elem.a.getId().equals(id)){
                            return elem.b.typeCheck(e);
                        }
                    }
                }else if(node instanceof StructType){
                    StructType structType = (StructType) node;
                    id = listId.get(1);
                    for(Pair<IdNode, TypeNode> elem : structType.getParams()){
                        if(elem.a.getId().equals(id)){
                            return elem.b.typeCheck(e);
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {

        ArrayList<String> error = new ArrayList<>();
        if(env.containsDeclaration(listId.get(0))){
            Node stcnode = env.getDeclaration(listId.get(0));
            if(stcnode instanceof StructNode) {
                StructNode structNode = (StructNode) stcnode;
                boolean flag = false;
                for( Pair<IdNode, TypeNode> elem : structNode.getParams()){
                    if (Objects.equals(elem.a.getId(), listId.get(1)) && !flag) {
                        flag = true;
                    }
                }
                if(!flag){
                    error.add(listId.get(1) +" is not declared");
                }
            }
        }else{
            error.add(listId.get(0) +" is not declared");
        }
        return error;
    }

    @Override
    public String toEquation(EnvVar e) {
        return null;
    }

}
