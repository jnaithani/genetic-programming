package data;

import utilities.NodeFactory;

public class Tree {
    Node root = null;
    
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
        } else {
            System.out.println("Tree is empty!");
        }
    }
    
    public static Node generateTree(int maxDepth) {
        double p = Math.random();
        
        Node root = null;
        
        if (p <= 0.5) {
             root = generateFull(maxDepth);
        } else {
            root = generateGrow(maxDepth);
        }
        
        return root;
    }
    
    private static Node generateFull(int maxDepth) {
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
    
    private static Node generateGrow(int maxDepth) {
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
