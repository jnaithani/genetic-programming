package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import org.junit.Test;

import data.GeneticProgrammingTree;
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

            Utilities.printTreeNode(tree.getRoot());
            
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
            GeneticProgrammingTree gpTree = GeneticProgrammingTree.createGeneticProgrammingTree(TrainingData.getTrainingData());    
            
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
    public void testSortGeneticProgammingPopulation() {
        System.out.println("***testSortGeneticProgammingPopulation***");
        
        int size = 0;
        
        try {
            Properties settings = Settings.getSettings();
            
            String prop = settings.getProperty(Settings.PROP_POPULATION_SIZE);
            
            size = Integer.parseInt(prop);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not load property '" + Settings.PROP_POPULATION_SIZE + "'");
        }
        
        ArrayList<GeneticProgrammingTree> population = new ArrayList<GeneticProgrammingTree>(size);
        try {
            for (int i = 0; i < size; i++) {
                GeneticProgrammingTree gpTree = GeneticProgrammingTree.createGeneticProgrammingTree(TrainingData.getTrainingData()); 
                
                Utilities.printTreeNode(gpTree.getRoot());
                
                gpTree.inOrderPrint();
                population.add(gpTree);
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not generate GeneticProgramming tree");
        }
        
        System.out.println("Unsorted population");
        for (GeneticProgrammingTree gpTree : population) {
            System.out.println("GP Tree Fitness: " + gpTree.getFitness());
        }
        
        Collections.sort(population);
        
        System.out.println("\nSorted population, in descending order");
        for (GeneticProgrammingTree gpTree : population) {
            System.out.println("GP Tree Fitness: " + gpTree.getFitness());
        }
    }
    
    @Test
    public void testGenerateInitialTree() {    
        System.out.println("***testGenerateInitialTree***");
        
        int depth = 0;
        int size = 0;
        
        try {
            Properties settings = Settings.getSettings();
            
            String prop = settings.getProperty(Settings.PROP_MAX_DEPTH);
            
            depth = Integer.parseInt(prop);
            
            prop = settings.getProperty(Settings.PROP_POPULATION_SIZE);
            
            size = Integer.parseInt(prop);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not load property '" + Settings.PROP_MAX_DEPTH + "'");
        }
        
        try {
            for (int i = 0; i < size; i++) {
                Tree gpTree = GeneticProgrammingTree.generateInitialTree(depth); 
                
                System.out.println("Evaluate: " + gpTree.evaluate(0));
                
                assertTrue("Could not generate a good initial tree", ((Double.isNaN(gpTree.evaluate(0)) || Double.isInfinite(gpTree.evaluate(0))) == false));
                
                System.out.println("Post Order Print");
                gpTree.postOrderPrint();
                
                System.out.println("\nTree Print");
                Utilities.printTreeNode(gpTree.getRoot());
                
                System.out.println("In Order Print");
                gpTree.inOrderPrint();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    @Test
    public void testPrintTree() {    
        System.out.println("***testPrintTree***");
        
        int depth = 0;
        int size = 0;
        
        try {
            Properties settings = Settings.getSettings();
            
            String prop = settings.getProperty(Settings.PROP_MAX_DEPTH);
            
            depth = Integer.parseInt(prop);
            
            prop = settings.getProperty(Settings.PROP_POPULATION_SIZE);
            
            size = Integer.parseInt(prop);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not load property '" + Settings.PROP_MAX_DEPTH + "'");
        }
        
        try {
            for (int i = 0; i < size; i++) {
                Tree gpTree = GeneticProgrammingTree.generateInitialTree(depth); 
                
                System.out.println("\nPrint Tree: " + i);

                Utilities.printTreeNode(gpTree.getRoot());
                
                gpTree.inOrderPrint();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
}
