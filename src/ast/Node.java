package ast;

import utilities.EnvVar;
import utilities.Environment;

import java.util.ArrayList;

public interface Node {
    EnvVar checkVarEQ(EnvVar e);

    Node typeCheck(Environment e);

    ArrayList<String> checkSemantics(Environment env);
    String toEquation(EnvVar e);

}
