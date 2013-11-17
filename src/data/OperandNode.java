package data;

import utilities.Settings;

public class OperandNode extends Node {
    
    public final static String OPERAND_X = "x";
    public final static int TERMINAL_SET_SIZE = 11;
    
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
