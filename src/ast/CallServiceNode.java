package ast;

import ast.typeNode.TypeNode;
import ast.typeNode.VoidType;
import org.antlr.v4.runtime.misc.Pair;
import utilities.EnvVar;
import utilities.Environment;
import utilities.Utils;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CallServiceNode implements Node {
    String idCall;
    ArrayList<Node> exp;
    Node stm;
    public CallServiceNode(String idCall, ArrayList<Node> exp,Node stm) {
        this.idCall = idCall;
        this.exp = exp;
        this.stm = stm;
    }
    public CallServiceNode(String idCall, ArrayList<Node> exp) {
        this.idCall = idCall;
        this.exp = exp;
        this.stm = stm;
    }
    @Override
    public String toPrint(String indent) {
        return indent + "CallServiceNode " + idCall + "\n" + indent + "Exp:\n" + exp.toString() + "\n" + indent + "Stm:\n" + stm.toString();
    }

    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        e.add(this, String.valueOf(idCall.charAt(0)));
        if(stm != null){
            e = stm.checkVarEQ(e);
        }
        return e;
    }

    @Override
    public Node typeCheck(Environment e) {
        DecService service = (DecService) e.getDeclaration(idCall);
        ArrayList<Pair<IdNode, TypeNode>> par = service.getParams();
        for(int i = 0 ;i< exp.size();i++){
            Node parType = par.get(i).a != null ? par.get(i).a : par.get(i).b;
            if(!Utils.isSubtype(exp.get(i).typeCheck(e),parType.typeCheck(e))  ){
               System.err.println("Incompatible type in "+ idCall+" node");
            }
        }
        return service.returnType.typeCheck(e);
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        ArrayList<String> errors = new ArrayList<>();
        if(!env.containsDeclaration(idCall)){
            errors.add(idCall +" is not declared");
        }
        if(exp != null) {
            for (Node n : exp) {
                errors.addAll(n.checkSemantics(env));
            }
        }
        if(stm != null){
            errors.addAll(stm.checkSemantics(env));
        }
        return errors;
    }

    @Override
    public String toEquation(EnvVar e) {
        if(stm != null) {
            return e.get(this) + "+" + stm.toEquation(e);
        }else
            return e.get(this);
    }
}
