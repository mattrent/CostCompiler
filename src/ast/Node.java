package ast;

import utilities.EnvVar;
import utilities.Environment;
import utilities.TypeErrorException;

import java.util.ArrayList;

public interface Node {
    EnvVar checkVarEQ(EnvVar e);

    Node typeCheck(Environment e) throws TypeErrorException;

    ArrayList<String> checkSemantics(Environment env);
    String toEquation(EnvVar e);

    String codeGeneration();
}
