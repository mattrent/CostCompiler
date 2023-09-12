package ast;

import utilities.EnvVar;
import utilities.Environment;
import utilities.Utils;

import java.util.ArrayList;

public class CallNode implements Node{

    IdNode id;
    ArrayList<Node> listCount;

    public CallNode(IdNode id, ArrayList<Node> listCount) {
        this.id = id;
        this.listCount = listCount;
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
    public Node typeCheck(Environment e) {

        FunDeclarationNode fun = (FunDeclarationNode) e.getDeclaration(id.getId());
        FormalParams fp = fun.getFormalParams();
        if(fp.size() != listCount.size()){
            System.err.println("Wrong number of parameters in call "+id.getId());
            System.exit(0);
        }
        for(int i =0;i<listCount.size();i++){
            if(!Utils.isSubtype(fp.get(i).b.typeCheck(e),listCount.get(i).typeCheck(e))) {
                System.err.println("Incompatible type in call node");
                System.exit(0);
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
        return null;
    }
}
