package ast.statement;

import ast.IdNode;
import ast.Node;
import ast.typeNode.VoidType;
import org.antlr.v4.runtime.misc.Pair;
import ast.typeNode.TypeNode;
import utilities.EnvVar;
import utilities.Environment;

import java.util.ArrayList;
import java.util.HashMap;

public class DecService implements Node {
    IdNode id;
    ArrayList<Pair<IdNode,TypeNode>> params;
    ReturnTypeNode returnType;

    public DecService(IdNode id, ArrayList<Pair<IdNode, TypeNode>> params, ReturnTypeNode returnType) {
        this.id = id;
        this.params = params;
        this.returnType = returnType;
    }

    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        e.add(new CallServiceNode(id.getId(),null));
        return e;
    }

    @Override
    public Node typeCheck(Environment e) {
        e.addDeclaration(id.getId(),this);
        return this;
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        ArrayList<String> errors = new ArrayList<>();
        env.addDeclaration(id.getId(),this);
        for (Pair<IdNode, TypeNode> elem : params) {
            if(elem != null && elem.a != null){
                if(!env.containsDeclaration(elem.a.getId()))
                    errors.add(elem.a.getId() + " is not declared");
            }
        }
        return errors;
    }

    @Override
    public String toEquation(EnvVar e) {
        return "";
    }

    @Override
    public String codeGeneration(HashMap<Node, Integer> offset_idx) {
        String serviceComment = String.format(";; service %s (%s) -> %s\n",
                        id.getId(),
                        params.stream()
                                .map(pair -> pair.b.getClass().getSimpleName())
                                .reduce(String::concat),
                        returnType.type.getClass().getSimpleName());
        /*
           TODO: handle call here; the service is defined as a function, calling __http_request with a specific string as URL;
           in FunLess, we receive the string and see if it exists as a key in the function's metadata (and is exported by the module);
           if it does, we parse the input as described by the export
        */
        String paramsCode = "";
        String returnCode = !(returnType.type instanceof VoidType) ?
                String.format("(result %s)", returnType.type.codeGeneration(offset_idx).replace(",", " ")) :
                "";
        String innerCode = "";
        String serviceDefinition = String.format("(func $%s %s %s %s)\n",
                id.getId(),
                paramsCode,
                returnCode,
                innerCode
                );
        String serviceExport = String.format("(export \"%s\" (func $%s))\n",
                id.getId(),
                id.getId());
        return serviceComment + serviceDefinition + serviceExport;
    }

    public ArrayList<Pair<IdNode, TypeNode>> getParams() {
        return params;
    }
}
