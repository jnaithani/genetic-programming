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
}
