package test;

import data.BinaryNode;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BinaryTreeTest {


    @Test
    public void testPostOrderItems() {
        BinaryNode root = new BinaryNode("*");
        root.setLeft(new BinaryNode("4"));
        root.setRight(new BinaryNode("6"));


        ArrayList items = BinaryNode.postOrderItems(root);

        assertEquals(3, items.size());
        assertEquals("6", items.get(1));
    }
}
