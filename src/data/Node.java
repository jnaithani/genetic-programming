package data;

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
    
    public abstract String getDataItem();
}
