package data;

import utilities.Utilities;

import java.util.ArrayList;

/**
 *
 */
public class BinaryNode {

    private String item;        // The data in this node. Items : [0-9, 'x' or +-*/
    private BinaryNode left;   // Pointer to the left subtree.
    private BinaryNode right;  // Pointer to the right subtree.
    private BinaryNode parent;  // Pointer to the parent node.
    private static ArrayList items = new ArrayList();

    /**
     *
     */
    public BinaryNode(BinaryNode right, BinaryNode left, String item) {
        this.item = item;
        this.left = left;
        this.right = right;
    }

    /**
     * Default with nothing
     */
    public BinaryNode(String item) {
        this.item = item;
    }

    public void setNodes(BinaryNode left, BinaryNode right) {
        this.left = left;
        this.right = right;
    }
    /*Custom Method Implementations */

    /**
     * Print all the items in the tree to which root points.
     * The item in the left subtree is printed first, followed
     * by the items in the right subtree and then the item
     * in the root node.
     */
    public static ArrayList postOrderItems(BinaryNode root) {


        if (root != null) {  // (Otherwise, there's nothing to print.)
            postOrderItems(root.left);   // Print items in left subtree.
            postOrderItems(root.right);  // Print items in right subtree.
            System.out.print(root.item + " ");  // Print the root item.
            items.add(root.item);
        }
        return items;
    } // end postorderPrint()

    /**
     * Will only return Y value when the current nodes item is an operator and child nodes are operands and not 'x'
     *
     * @return
     */
    public double evaluateNode() {

        double yValue = 0;

        //check if this.rightNode and this.Left Node are operands
        if (this.right != null) {
            if (!isOperand(this.item) && this.item != "x") {
                int leftValue = Integer.parseInt(this.left.item);
                int rightValue = Integer.parseInt(this.right.item);

                //compute

                yValue = Utilities.performOperation(this.item.charAt(0), leftValue, rightValue);
            }


        }
        return yValue;
    }

    /**
     * Has to be number to be parsed and caught by NumberFormatException
     *
     * @return
     */
    public boolean isOperand(String item) {

        try {
            Integer.parseInt(item);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* Getters*/
    public BinaryNode getParent() {
        return parent;
    }

    public BinaryNode getRight() {
        return right;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public String getItem() {
        return item;
    }

    /* Setters */
    public void setItem(String item) {
        this.item = item;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
    }
}
