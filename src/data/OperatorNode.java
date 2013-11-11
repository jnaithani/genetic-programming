package data;

import utilities.Settings;

public class OperatorNode extends Node {
    
    public final static String OP_ADD = "+";
    public final static String OP_SUB = "-";
    public final static String OP_MUL = "*";
    public final static String OP_DIV = "/";
    
    private String operator;

    public OperatorNode()
    {
        double probability = Math.random();

        if (probability <= .25) {
            operator = OP_ADD;
        }
        else if (probability > .25 && probability <= .50) {
            operator = OP_SUB;
        }
        else if (probability > .50 && probability <= .75) {
            operator = OP_MUL;
        }
        else {
            operator = OP_DIV;
        }
        
        setNumChildNodes(2);
        
        if (Settings.debug()) {
            System.out.println(operator);
        }
    }
    
    public String getDataItem() {
        return operator;
    }
    
    public String toString() {
        return operator;
    }
    
    public double evaluate(double xval) {
        double left = getLeftChild().evaluate(xval);
        double right = getRightChild().evaluate(xval);
        
        return calculate(left, right, operator);
    }
    
    private double calculate(double left, double right, String operator) {
        if (operator.equals(OP_ADD)) {
            return left + right;
        } else if (operator.equals(OP_SUB)) {
            return left - right;
        } else if (operator.equals(OP_MUL)) {
            return left * right;
        } else if (operator.equals(OP_DIV)) {
            return left / right;
        }
        
        return 0;
    }
}
