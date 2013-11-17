package data;

import java.util.ArrayList;

import utilities.NodeFactory;

public class Tree {
    Node root = null;
    
    public Node getRoot() {
		return root;
	}

	public Tree(Node root) {
        this.root = root;
    }
    
    public boolean isEmpty() {
        return root == null;
    }
    
    public int depth() {
        return depth(root);
    }

    private int depth(Node root) {
        if (root == null)
            return 0;
        else
            return 1 + Math.max(depth(root.getLeftChild()), depth(root.getRightChild()));
    }
    
    public int size() {
        return size(root);
    }

    private int size(Node root) {
        if (root == null)
            return 0;
        else
            return 1 + size(root.getLeftChild()) + size(root.getRightChild());
    }
    
    public void postOrderPrint() {
        if (root != null) {
            root.postOrderPrint();
            System.out.println("");
        } else {
            System.out.println("Tree is empty!");
        }
    }
    
    public void inOrderPrint() {
        if (root != null) {
            root.inOrderPrint();
            System.out.println("");
        } else {
            System.out.println("Tree is empty!");
        }
    }
    
    public double evaluate(double xval) throws Exception {
        return root.evaluate(xval);
    }
    
    public boolean exists(Node node) throws Exception {
        ArrayList<Node> nodeList = new ArrayList<Node>(this.size());
        nodeList = this.getRoot().getNodeList(nodeList, node);
        
        for (Node n : nodeList) {
            if (node.equals(n)) {
                return true;
            }
        }
        
        return false;
    }
    
    public static Tree generateTree(int maxDepth) throws Exception{
        double p = Math.random();
        
        Node root = null;
        
        //x probability < p 
        if (p <= 0.5) {
             root = generateFull(maxDepth);
        } else {
            root = generateGrow(maxDepth);
        }
        
        return new Tree(root);
    }
    
    private static Node generateFull(int maxDepth) throws Exception {
        Node root;
        
        if (maxDepth > 1) {
            root = NodeFactory.getNode();  
        } else {
            root = NodeFactory.getTerminal();  
        }
        
        for(int i = 0; i < root.numChildNodes(); i++)  {
            if (i == 0) {
                root.setLeftChild(generateFull(maxDepth - 1));
            } else {
                root.setRightChild(generateFull(maxDepth - 1));
            }
        }
           
        return root;  
    }
    
    private static Node generateGrow(int maxDepth) throws Exception {
        Node root;
        
        if (maxDepth > 1) {
            root = NodeFactory.getFunction();  
        } else {
            root = NodeFactory.getTerminal();  
        }
        
        for(int i = 0; i < root.numChildNodes(); i++)  {
            if (i == 0) {
                root.setLeftChild(generateGrow(maxDepth - 1));
            } else {
                root.setRightChild(generateGrow(maxDepth - 1));
            }
        }
           
        return root;  
    }
}
