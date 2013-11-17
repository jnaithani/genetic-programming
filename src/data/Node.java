package data;

import java.util.ArrayList;

public abstract class Node {
    public final static int MAX_NUM_CHILD_NODES = 2;
    
    private Node parent = null;
    private Node leftChild = null;
    private Node rightChild = null;
    private int numChildNodes = 0;

    public void setParent(Node p) {
        parent = p;
    }

    public void setLeftChild(Node lc) {
        lc.setParent(this);
        leftChild = lc;
    }

    public void setRightChild(Node rc) {
        rc.setParent(this);
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
    
    public void postOrderPrint() {
        if (getLeftChild() != null) {
            getLeftChild().postOrderPrint();
        }
        
        if (getRightChild() != null) {
            getRightChild().postOrderPrint();  
        }
        
        System.out.print(this.getDataItem() + " ");  
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
    
    public abstract String getDataItem();
    
    public abstract double evaluate(double xval);
}
