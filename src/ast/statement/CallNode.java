package ast.statement;

import ast.IdNode;
import ast.Node;
import utilities.EnvVar;
import utilities.Environment;
import utilities.TypeErrorException;
import utilities.Utils;

import java.util.ArrayList;
import java.util.Objects;

import static utilities.Utils.getFunDecNodeByLine;

public class CallNode implements Node {

    IdNode id;
    ArrayList<Node> listCount;
    int line;
    public CallNode(IdNode id, ArrayList<Node> listCount, int line) {
        this.id = id;
        this.listCount = listCount;
        this.line = line;
    }


    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        return e;
    }

    @Override
    public Node typeCheck(Environment e) throws TypeErrorException {
        try {
            FunDeclarationNode fun = (FunDeclarationNode) e.getDeclaration(id.getId());
            FormalParams fp = fun.getFormalParams();
            if (fp.size() != listCount.size()) {
                throw new TypeErrorException("Wrong number of parameters in call " + id.getId());
            }
            for (int i = 0; i < listCount.size(); i++) {
                if (!Utils.isSubtype(fp.get(i).b.typeCheck(e), listCount.get(i).typeCheck(e))) {
                    throw new TypeErrorException("Incompatible type in call node");
                }
            }
            return fun.getReturnNode().typeCheck(e);
        }catch (ClassCastException ex){
            throw new TypeErrorException("Error in call node");
        }
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

    @Override
    public String codeGeneration() {
        StringBuilder params = new StringBuilder();
        for (int i= listCount.size()-1; i>=0; i--){
             params.append(listCount.get(i).codeGeneration());
        }
        return params + "(call $"+id.getId()+")\n ";
    }

    public String getId() {
        return id.getId();
    }
}
