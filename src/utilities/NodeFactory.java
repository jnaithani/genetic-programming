package utilities;

import data.Node;
import data.OperandNode;
import data.OperatorNode;

public class NodeFactory {

    public static Node getNode() {
        double p = Math.random();

        if (p <= .50) {
            return getOperator();
        } else {
            return getOperand();
        }
    }
    
    public static Node getTerminal() {
        return new OperandNode();
    }
    
    public static Node getFunction() {
        return new OperatorNode();
    }
    
    private static Node getOperand() {
        return new OperandNode();
    }
    
    private static Node getOperator() {
        return new OperatorNode();
    }
}
