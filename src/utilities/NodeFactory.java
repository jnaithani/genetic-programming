package utilities;

import java.util.Random;

import data.Node;
import data.OperandNode;
import data.OperatorNode;

public class NodeFactory {

    public static final int MAX = 15;
    
    public static Node getNode() throws Exception {
        return getRandomNode();
    }

    public static Node getTerminal() throws Exception {
        if (getMaxLeafNodes() == 1) {
            return new OperandNode(OperandNode.OPERAND_X);
        } else {
            return getOperand();
        }
    }
    
    public static Node getFunction() throws Exception {
        return getOperator();
    }
    
    private static Node getRandomNode() throws Exception {
        double p = Math.random();
    
        if (getMaxLeafNodes() == 1) {
            return new OperandNode(OperandNode.OPERAND_X);
        } else {
            if (p <= .50) {
                return NodeFactory.getOperator();
            } else {
                return NodeFactory.getOperand();
            }
        }
    }
    
    private static Node getOperand() throws Exception {
        return getRandomOperandNode();
    }
    
    private static Node getOperator() throws Exception {
        return getRandomOperatorNode();
    }
    
    private static Node getRandomOperandNode() throws Exception {
        String operand = getRandomOperand();
        return new OperandNode(operand);
    }
    
    private static Node getRandomOperatorNode() throws Exception {
        String operator = getRandomOperator();
        return new OperatorNode(operator);
    }
    
    private static String getRandomOperand() throws Exception {    
        int maxLeafNodes = getMaxLeafNodes();
        
        if (maxLeafNodes == 1) {
            return OperandNode.OPERAND_X;
        }
        else {
            Random random = new Random();
            
            if (maxLeafNodes < OperandNode.TERMINAL_SET_SIZE) {
                if (Settings.debug()) {
                    System.out.println("maxLeafNodes: " + maxLeafNodes);
                }
                
                int randomX = random.nextInt(maxLeafNodes);
                
                if (Settings.debug()) {
                    System.out.println("randomX: " + randomX);
                }
                
                if (randomX < maxLeafNodes/2) {
                    return OperandNode.OPERAND_X;
                } else {
                    return Integer.toString(random.nextInt(OperandNode.TERMINAL_SET_SIZE - 1));
                }
            } else {
                int randInt = random.nextInt(OperandNode.TERMINAL_SET_SIZE);
                
                if (Settings.debug()) {
                    System.out.println("randomInt: " + randInt);
                }
                
                if (randInt <= OperandNode.TERMINAL_SET_SIZE - 1) {
                    return Integer.toString(random.nextInt(OperandNode.TERMINAL_SET_SIZE - 1));
                } else {
                    return OperandNode.OPERAND_X;
                }
            }
        }
    }

    public static int getMaxLeafNodes() throws Exception {
        int depth = Settings.getMaxDepth();
        
        int maxLeafNodes = (int) Math.pow(2, (depth - 1));
        return maxLeafNodes;
    }
    
    private static String getRandomOperator() throws Exception {
        String operator = null;
        double probability = Math.random();

        if (probability <= .25) {
            operator = OperatorNode.OP_ADD;
        }
        else if (probability > .25 && probability <= .50) {
            operator = OperatorNode.OP_SUB;
        }
        else if (probability > .50 && probability <= .75) {
            operator = OperatorNode.OP_MUL;
        }
        else {
            operator = OperatorNode.OP_DIV;
        }
        
        return operator;
    }
}
