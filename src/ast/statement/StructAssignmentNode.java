package ast.statement;

import ast.IdNode;
import ast.Node;
import ast.StructNode;
import ast.typeNode.StructType;
import ast.typeNode.TypeNode;
import org.antlr.v4.runtime.misc.Pair;
import utilities.EnvVar;
import utilities.Environment;
import utilities.TypeErrorException;
import utilities.Utils;

import java.util.ArrayList;

public class StructAssignmentNode implements Node {

    private IdNode id;
    private ArrayList<LetAssignmentNode> assign;

    public StructAssignmentNode(IdNode id, ArrayList<LetAssignmentNode> assign) {
        this.id = id;
        this.assign = assign;
    }

    @Override
    public EnvVar checkVarEQ(EnvVar e) {
        for (LetAssignmentNode n : assign) {
            n.checkVarEQ(e);
        }
        return e;
    }

    @Override
    public Node typeCheck(Environment e) throws TypeErrorException {
        StructType st = (StructType) e.getDeclaration(id.getId());
        for (LetAssignmentNode n : assign) {
            TypeNode t = st.getType(n.getId());

            if(!Utils.isSubtype(t,n.getAss()))
                throw new TypeErrorException("Incompatible type in assignment Node");

        }

        return st;
    }

    @Override
    public ArrayList<String> checkSemantics(Environment env) {
        ArrayList<String> errors = new ArrayList<>();
        for (LetAssignmentNode n : assign) {
             errors.addAll(n.checkSemantics(env));
        }
        return errors;
    }

    @Override
    public String toEquation(EnvVar e) {
        String eq = "";
        for (LetAssignmentNode n : assign) {
            eq += n.toEquation(e);
        }
        return eq;
    }
}
