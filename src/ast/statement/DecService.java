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
        return this.codeGeneration(offset_idx, 0);
    }

    public String codeGeneration(HashMap<Node, Integer> offset_idx, int serviceIdx) {
        String serviceComment = String.format(";; service %s (%s) -> %s\n",
                        id.getId(),
                        params.stream()
                                .map(pair -> pair.b.getClass().getSimpleName())
                                .reduce(String::concat),
                        returnType.type.getClass().getSimpleName());

        StringBuilder innerCode = new StringBuilder();
        //StringBuilder paramsCode = new StringBuilder("(param $__first_available_ptr i32)");
        StringBuilder paramsCode = new StringBuilder();
        // set input parameters types and store parameters in memory
        int allocated = 0;
        int totalParams = 0;
        if (params != null) {
            for (Pair<IdNode, TypeNode> n : params) {
                String[] wasmType = n.b.codeGeneration(offset_idx).split(",");
                String paramString;
                String loadParamString = "";

                if (wasmType.length == 1) {
                    paramString = n.a == null ?
                            String.format(" (param %s)", wasmType[0]) :
                            String.format(" (param $%s %s)", n.a.getId(), wasmType[0]);
                    paramsCode.append(paramString);

                    loadParamString +=
                            "global.get $__first_available_ptr\n" +
                            String.format("i32.const %d\n", allocated) +
                                    "i32.add\n";

                    if (n.a == null) {
                        loadParamString += String.format("local.get %d\n", totalParams);
                    } else {
                        loadParamString += String.format("local.get $%s\n", n.a.getId());
                    }

                    if (wasmType[0].equals("f64")) {
                        loadParamString += "f64.store\n";
                        allocated += 8;
                    } else {
                        loadParamString += "i32.store\n";
                        allocated += 4;
                    }

                    totalParams += 1;
                    innerCode.append(loadParamString);
                } else {
                    paramString = n.a == null ?
                            String.format(" (param %s) (param %s)", wasmType[0], wasmType[1]) :
                            String.format(" (param $%s %s) (param $%s %s)", n.a.getId() + "_ptr", wasmType[0], n.a.getId() + "_len", wasmType[1]);
                    paramsCode.append(paramString);


                    for (int i = 0; i < 2; i++) {
                        loadParamString +=
                                "global.get $__first_available_ptr\n" +
                                String.format("i32.const %d\n", allocated) +
                                        "i32.add\n";

                        if (n.a == null) {
                            loadParamString += String.format("local.get %d\n", totalParams + i);
                        } else {
                            loadParamString += String.format("local.get $%s\n", n.a.getId());
                        }

                        loadParamString += "i32.store\n";
                        allocated += 4;
                    }

                    totalParams += 2;
                    innerCode.append(loadParamString);
                }

            }
        }
        String resultCode =
                String.format("(result %s)\n", returnType.type.codeGeneration(offset_idx).replace(",", " "));

        // pass parameters to HTTP request
        innerCode.append(
                "global.get $__first_available_ptr\n" + // pointer to response body (__first_available_ptr)
                "i32.const -1\n" + // pointer to response length (ignored)
                "i32.const -1\n" + // pointer to status
                "i32.const -1\n" + // method
                String.format("i32.const %d\n", serviceIdx) + // URL (we pass the service index, function metadata contains the actual URL)
                "i32.const -1\n" + // URL length
                "i32.const -1\n" + // pointer to headers
                "i32.const -1\n" + // pointer to header length
                "global.get $__first_available_ptr\n" + // pointer to body (__first_available_ptr)
                "i32.const -1\n" + // body length (ignored, size of body is in service params)
                "call $__http_request\n");

        // parse response body and return result (extract values from memory)
        String[] wasmReturnType = returnType.type.codeGeneration(offset_idx).split(",");
        int offset = 0;
        for (String s : wasmReturnType) {
            innerCode.append("global.get $__first_available_ptr\n");
            innerCode.append(String.format("i32.const %d\n", offset));
            innerCode.append("i32.add\n");
            innerCode.append(String.format("%s.load\n", s));
        }
        innerCode.append("return\n");

        String serviceDefinition = String.format("(func $%s %s %s %s)\n",
                id.getId(),
                paramsCode,
                resultCode,
                innerCode
                );
        /*
        String serviceExport = String.format("(export \"%s\" (func $%s))\n",
                id.getId(),
                id.getId());
        */
        return serviceComment + serviceDefinition;
    }

    public ArrayList<Pair<IdNode, TypeNode>> getParams() {
        return params;
    }
}
