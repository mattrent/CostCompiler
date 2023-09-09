package ast;

import utilities.EnvVar;
import utilities.Environment;

import java.util.ArrayList;

public interface Node {
    String toPrint(String indent);

    EnvVar checkVarEQ(EnvVar e);

    ArrayList<String> checkSemantics(Environment env);
    String toEquation(EnvVar e);

}
