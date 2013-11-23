package data;

import utilities.Settings;

public class OperandNode extends Node {
    
    public final static String OPERAND_X = "x";
    public final static int TERMINAL_SET_SIZE = 11;
    public final static int TERMINAL_CONSTANTS_SIZE = 10;
    
    private String operand;

    public OperandNode(String operand) throws Exception {
        this.operand = operand;
        
        if (Settings.trace()) {
            System.out.println(operand);
        }
    }
    
    public String getOperand() {
        return operand;
    }
    
    public String getDataItem() {
        return operand;
    }
    
    public Node getClone() throws Exception {
        Node clone = new OperandNode(operand);
        clone.setLeftChild(getLeftChild());
        clone.setRightChild(getRightChild());

        return clone;
    }
    
    public void setDataItem(String item) {
        operand = item;
    }
    
    public String toString() {
        return operand;
    }
    
    public double evaluate(double xval) {
        if (operand.equals(OPERAND_X)) {
            return xval;
        } else {
            return Double.parseDouble(operand);
        }
    }
}
