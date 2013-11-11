package test;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Test;

import data.Node;
import data.Tree;
import utilities.Settings;

public class TreeTest {

    @Test
    public void testGenerateTree() {
        int maxHeight = 0;
        
        try {
            Properties settings = Settings.getSettings();
            
            String prop = settings.getProperty(Settings.PROP_MAX_DEPTH);
            
            maxHeight = Integer.parseInt(prop);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not load print '" + Settings.PROP_MAX_DEPTH + "'");
        }
        
        try {
            Node root = Tree.generateTree(maxHeight);
            Tree tree = new Tree(root);    
            
            System.out.println("Tree depth: " + tree.depth());
            System.out.println("Tree size: " + tree.size());
            
            tree.postOrderPrint();  
            System.out.println("");
            
            double xval = 1;
            System.out.println("Evaluate: " + tree.evaluate(xval));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not generate tree");
        }
    }

}
