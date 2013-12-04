package test;

import data.OperandNode;
import data.OperatorNode;
import data.Tree;
import org.junit.Test;
import static org.junit.Assert.*;


public class NodeTest {



    @Test
    public void getTreeNodes() throws Exception {

        OperandNode left = new OperandNode(String.valueOf(-10));
        OperandNode right = new OperandNode(String.valueOf(7));
        OperatorNode first1 = new OperatorNode("+");

        first1.setLeftChild(left);
        first1.setRightChild(right);
        Tree tree = new Tree(first1);

        //assert number of nodes
        int nodeCount = 3;
        assertEquals(nodeCount, tree.getAllNodes().size());
    }

    @Test
    public void getOperandNodes() throws Exception {

        OperandNode left = new OperandNode(String.valueOf(-10));
        OperandNode right = new OperandNode(String.valueOf(7));
        OperatorNode first1 = new OperatorNode("+");

        first1.setLeftChild(left);
        first1.setRightChild(right);
        Tree tree = new Tree(first1);

        int nodeCount = 2;
        assertEquals(nodeCount, tree.getOperandNodes().size());
    }

    @Test
    public void getOperatorNodes() throws Exception {

        OperandNode left = new OperandNode(String.valueOf(-10));
        OperandNode right = new OperandNode(String.valueOf(7));
        OperatorNode first1 = new OperatorNode("+");

        first1.setLeftChild(left);
        first1.setRightChild(right);
        Tree tree = new Tree(first1);

        int nodeCount = 1;
        assertEquals(nodeCount, tree.getOperatorNodes().size());
    }
}
