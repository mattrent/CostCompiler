package ast.statement;

import ast.IdNode;
import ast.Node;
import utilities.EnvVar;
import utilities.Environment;
import utilities.TypeErrorException;
import utilities.Utils;

import java.util.ArrayList;

public class CallNode implements Node {

    IdNode id;
    ArrayList<Node> listCount;

    public CallNode(IdNode id, ArrayList<Node> listCount) {
        this.id = id;
        this.listCount = listCount;
    }


    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        return e;
    }

    @Override
    public Node typeCheck(Environment e) throws TypeErrorException {

        FunDeclarationNode fun = (FunDeclarationNode) e.getDeclaration(id.getId());
        FormalParams fp = fun.getFormalParams();
        if(fp.size() != listCount.size()){
            throw new TypeErrorException("Wrong number of parameters in call "+id.getId());
        }
        for(int i =0;i<listCount.size();i++){
            if(!Utils.isSubtype(fp.get(i).b.typeCheck(e),listCount.get(i).typeCheck(e))) {
                throw new TypeErrorException("Incompatible type in call node");
            }
        }
        return fun.getReturnNode().typeCheck(e);
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        ArrayList<String> error = new ArrayList<>();

        if(!env.containsDeclaration(id.getId())){
            error.add(id.getId() +" is not declared");
        }

        return error;

    }

    @Override
    public String toEquation(EnvVar e) {
        FunDeclarationNode fun = (FunDeclarationNode) e.getFunDecNode(id.getId());

        assert fun != null;
        return this.id.getId() + "("+ fun.getParFun()+")";
    }

    public String getId() {
        return id.getId();
    }
}
