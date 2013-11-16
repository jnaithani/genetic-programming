package test;

import static org.junit.Assert.*;

import java.util.Properties;

import org.junit.Test;

import data.GeneticProgrammingTree;
import data.Node;
import data.TrainingData;
import data.Tree;
import utilities.Settings;
import utilities.Utilities;

public class TreeTest {

    @Test
    public void testGenerateTree() {
        System.out.println("***testGenerateTree***");
        int maxHeight = 0;
        
        try {
            Properties settings = Settings.getSettings();
            
            String prop = settings.getProperty(Settings.PROP_MAX_DEPTH);
            
            maxHeight = Integer.parseInt(prop);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not load property '" + Settings.PROP_MAX_DEPTH + "'");
        }
        
        try {
            Tree tree = Tree.generateTree(maxHeight);;    
            
            System.out.println("Tree depth: " + tree.depth());
            System.out.println("Tree size: " + tree.size());
            
            tree.postOrderPrint();  
            System.out.println("");
            //Utilities.printTreeNode(tree.getRoot());
            
            double xval = 1;
            System.out.println("Evaluate: " + tree.evaluate(xval));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not generate tree");
        }
    }
    
    @Test
    public void testCreateGeneticProgammingTree() {
        System.out.println("***testCreateGeneticProgammingTree***");
        
        try {
            GeneticProgrammingTree gpTree = GeneticProgrammingTree.createGeneticProgrammingTree(TrainingData.getTrainingData());;    
            
            System.out.println("Tree depth: " + gpTree.depth());
            System.out.println("Tree size: " + gpTree.size());
            
            gpTree.postOrderPrint();  
            System.out.println("");
            
            double xval = 1;
            System.out.println("Evaluate: " + gpTree.evaluate(xval));
            
            System.out.println("Fitness: " + gpTree.getFitness());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not generate GeneticProgramming tree");
        }
    }
    
    @Test
    public void testPrintTree() {     
        assertEquals(1,1);
    }
}
