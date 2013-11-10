package data;

import java.util.Random;

import utilities.Settings;

public class OperandNode extends Node {
    
    private String operand;

    public OperandNode() {
        Random rand = new Random();
        int randInt = rand.nextInt(11   );
        
        if (randInt == 10) {
            operand = "x";
        } else {
            operand = Integer.toString(randInt);
        }
        
        if (Settings.debug()) {
            System.out.println(operand);
        }
    }

    public void setOperand(String o) {
        operand = o;
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
}
