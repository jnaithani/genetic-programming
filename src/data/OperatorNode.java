package data;

import utilities.Settings;

public class OperatorNode extends Node {
    
    public final static String OP_ADD = "+";
    public final static String OP_SUB = "-";
    public final static String OP_MUL = "*";
    public final static String OP_DIV = "/";
    
    private String operator;

    public OperatorNode(String operator)
    {
        this.operator = operator;
        
        setNumChildNodes(2);
        
        if (Settings.trace()) {
            System.out.println(operator);
        }
    }
    
    public String getDataItem() {
        return operator;
    }
    
    public Node getClone() throws Exception {
        Node clone = new OperatorNode(operator);
        clone.setLeftChild(getLeftChild());
        clone.setRightChild(getRightChild());

        return clone;
    }
    
    protected void setDataItem(String item) {
        operator = item;
    }
    
    public String toString() {
        return operator;
    }
    
    public double evaluate(double xval) {
        double left = getLeftChild().evaluate(xval);
        double right = getRightChild().evaluate(xval);
        
        if (Settings.trace()) {
            System.out.println("calculate(left, right, " + operator + "): " + calculate(left, right, operator));
        }
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
