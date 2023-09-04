package ast;

public interface Node {
    String toPrint(String indent);

    Environment checkSemantics(Environment e);

    String toEquation(Environment e);

}
