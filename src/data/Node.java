package data;

import utilities.Settings;

import java.util.ArrayList;

public abstract class Node {
    public final static int MAX_NUM_CHILD_NODES = 2;
    
    private Node parent = null;
    private Node leftChild = null;
    private Node rightChild = null;
    private int numChildNodes = 0;
    private static ArrayList<String> items = new ArrayList<String>();

    public void setParent(Node p) {
        parent = p;
    }
    
    public Node getParent() {
        return parent;
    }

    public void setLeftChild(Node lc) {
        if (lc != null) {
            lc.setParent(this);
        }
        leftChild = lc;
    }

    public void setRightChild(Node rc) {
        if (rc != null) {
            rc.setParent(this);
        }
        rightChild = rc;
    }
    
    void setNumChildNodes(int n) {
        numChildNodes = n;
    }
    
    public Node getLeftChild() {
        return leftChild;
    }

    public Node getRightChild() {
        return rightChild;
    }
    
    public int numChildNodes() {
        return numChildNodes;
    }
    /**
     * Print all the items in the tree to which root points.
     * The item in the left subtree is printed first, followed
     * by the items in the right subtree and then the item
     * in the root node.
     */
    public static ArrayList<String> postOrderItems(Node root) {


        if (root != null) {  // (Otherwise, there's nothing to print.)
            postOrderItems(root.leftChild);   // Print items in left subtree.
            postOrderItems(root.rightChild);  // Print items in right subtree.
            System.out.print(root.getDataItem() + " ");  // Print the root item.
            items.add(root.getDataItem());
        }
        return items;
    }

    public String postOrderPrint() {
        if (getLeftChild() != null) {
            getLeftChild().postOrderPrint();
        }
        
        if (getRightChild() != null) {
            getRightChild().postOrderPrint();  
        }
        System.out.print(this.getDataItem() + " ");
        return this.getDataItem();
    }
    
    public void inOrderPrint() {
        if (getLeftChild() != null) {
            System.out.print("(");
            getLeftChild().inOrderPrint();
        }

        System.out.print(" " + this.getDataItem() + " ");  

        if (getRightChild() != null) {
            getRightChild().inOrderPrint();  
            System.out.print(")");
        }
    }
    
    public String getExpression() {
        StringBuffer sb = new StringBuffer();
        if (getLeftChild() != null) {
            sb.append("(");
            sb.append(getLeftChild().getExpression());
        }

        sb.append(this.getDataItem());
        
        if (getRightChild() != null) {
            sb.append(getRightChild().getExpression());  
            sb.append(")");
        }
        
        return sb.toString();
    }
    
    public ArrayList<Node> getNodeList(ArrayList<Node> nodeList) {
        if (getLeftChild() != null) { 
            nodeList = getLeftChild().getNodeList(nodeList);
        }
        
        if (getRightChild() != null) {
            nodeList = getRightChild().getNodeList(nodeList);
        }

        nodeList.add(this);

        return nodeList;
    }
    
    public ArrayList<Node> getOperatorNodeList(ArrayList<Node> nodeList) {
        if (getLeftChild() != null) { 
            nodeList = getLeftChild().getOperatorNodeList(nodeList);
        }
        
        if (getRightChild() != null) {
            nodeList = getRightChild().getOperatorNodeList(nodeList);
        }

        if (this instanceof OperatorNode) {
            if (Settings.trace()) {
                System.out.println("[Trace] Operator: " + this.getDataItem());
            }
            nodeList.add(this);
        }

        return nodeList;
    }
    
    public int depth() throws Exception {
        if (numChildNodes != 0) {
            if (getLeftChild() != null && getRightChild() != null) {
                return 1 + Math.max(getLeftChild().depth(), getRightChild().depth());
            } else {
                throw new Exception("Error:  Number of child nodes not 0, and one of the child nodes is null.");
            } 
        } else {
            return 1;
        }
    }
    
    public ArrayList<Node> getOperandNodeList(ArrayList<Node> nodeList) {
        if (getLeftChild() != null) { 
            nodeList = getLeftChild().getOperandNodeList(nodeList);
        }
        
        if (getRightChild() != null) {
            nodeList = getRightChild().getOperandNodeList(nodeList);
        }

        if (this instanceof OperandNode) {
            if (Settings.trace()) {
                System.out.println("Operand: " + this.getDataItem());
            }
            nodeList.add(this);
        }

        return nodeList;
    }
     
    public static void swap(Node nodeOne, Node nodeTwo) throws Exception {
        Node parentNodeOne = nodeOne.getParent();
        Node parentNodeTwo = nodeTwo.getParent();
        
        if (parentNodeOne != null) {
            if (parentNodeOne.getLeftChild() != null && parentNodeOne.getLeftChild() == nodeOne) {
                parentNodeOne.setLeftChild(nodeTwo);
            } else {
                if (parentNodeOne.getRightChild() != null && parentNodeOne.getRightChild() == nodeOne)
                parentNodeOne.setRightChild(nodeTwo);
            }
        } 
        
        if (parentNodeTwo != null) {
            if (parentNodeTwo.getLeftChild() != null && parentNodeTwo.getLeftChild() == nodeTwo) {
                parentNodeTwo.setLeftChild(nodeOne);
            } else {
                if (parentNodeTwo.getRightChild() != null && parentNodeTwo.getRightChild() == nodeTwo)
                parentNodeTwo.setRightChild(nodeTwo);
            }
        }
    }
    
    public static void update(Node targetNode, Node sourceNode) throws Exception {
        targetNode.setDataItem(sourceNode.getDataItem());
        targetNode.setLeftChild(sourceNode.getLeftChild());
        targetNode.setRightChild(sourceNode.getRightChild());        
    }
    
    public static void replace(Node targetNode, Node sourceNode) throws Exception {
        targetNode.setDataItem(sourceNode.getDataItem());
        targetNode.setLeftChild(sourceNode.getLeftChild());
        targetNode.setRightChild(sourceNode.getRightChild());        
    }
    
    public abstract void setDataItem(String item);
    
    public abstract String getDataItem();
    
    public abstract double evaluate(double xval);
    
    public abstract Node getClone() throws Exception;
    
    public abstract Node copy() throws Exception;
}
