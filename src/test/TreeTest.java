package test;

import data.*;
import org.junit.Test;
import utilities.GeneticOperators;
import utilities.NodeFactory;
import utilities.Settings;
import utilities.Utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import static org.hamcrest.CoreMatchers.instanceOf;

import static org.junit.Assert.*;

public class TreeTest {

    @Test
    public void testGenerateTree() {
        System.out.println("***testGenerateTree***");
        int maxHeight = 3;

        try {
            Tree tree = Tree.generateTree(maxHeight);
            ;

            System.out.println("Tree depth: " + tree.depth());

            tree.postOrderPrint();

            Utilities.printTreeNode(tree.getRoot());

            double xval = 1;
            System.out.println("Evaluate: " + tree.evaluate(xval));
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not generate tree");
        }
    }

    /**
     * @throws Exception
     */
    @Test
    public void testTreeDepth() throws Exception {
        OperandNode left = new OperandNode(String.valueOf(-10));
        OperandNode right = new OperandNode(String.valueOf(7));
        OperatorNode first1 = new OperatorNode("+");

        first1.setLeftChild(left);
        first1.setRightChild(right);
        Tree tree = new Tree(first1);

        assertEquals("Test tree depth of 2.", 2, tree.depth());
    }

    /**
     * @throws Exception
     */
    @Test
    public void testTreeFitness() throws Exception {
        OperandNode left = new OperandNode(String.valueOf(2));
        OperandNode right = new OperandNode(String.valueOf(2));

        OperatorNode plusOperator = new OperatorNode("+");
        plusOperator.setLeftChild(left);
        plusOperator.setRightChild(right);
        Tree tree = new Tree(plusOperator);

        double result = tree.evaluate(5);
        assertEquals("Evaluate (2 + 2) tree on x = 5", 4, result, 0.0001);
    }

    /**
     * @throws Exception
     */
    @Test
    public void testGPTreeMaxDepth() throws Exception {
        Node node;

        int maxDepth = Node.MAX_NUM_CHILD_NODES;

        for (int i = 0; i < 5; i++) {
            node = NodeFactory.getTerminal();

            if (node.depth() > maxDepth) {
                fail("Failed calculation - value of: " + maxDepth + " and node.depth: " + node.depth());
            }
        }
    }

    /**
     * Test the tree generation to make sure root is always an operator
     * @throws Exception
     */
    @Test
    public void testOperatorFirst() throws Exception{

        int maxDepth = 4;

        for(int i = 0; i < 100; i++){
            Tree gpTree = GeneticProgrammingTree.generateTree(maxDepth);

            if (gpTree.getRoot().depth() > 1)
                assertThat(gpTree.getRoot(), instanceOf(OperatorNode.class));
            else
                assertThat(gpTree.getRoot(), instanceOf(OperandNode.class));
        }

    }
    @Test
    public void testCreateGeneticProgammingTree() {
        System.out.println("***testCreateGeneticProgammingTree***");

        try {
            GeneticProgrammingTree gpTree = GeneticProgrammingTree.createGeneticProgrammingTree(TrainingData.getTrainingData());

            System.out.println("Tree depth: " + gpTree.depth());

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

    @Test
    public void testGetSortedPopulation() {
        System.out.println("***testGetSortedPopulation***");

        int size = 0;

        try {
            Properties settings = Settings.getSettings();

            String prop = settings.getProperty(Settings.PROP_POPULATION_SIZE);

            size = Integer.parseInt(prop);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not load property '" + Settings.PROP_POPULATION_SIZE + "'");
        }

        ArrayList<GeneticProgrammingTree> population = null;
        try {
            population = GeneticProgrammingTree.getGeneticTreePopulation(size);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not generate GeneticProgramming tree");
        }

        Collections.sort(population);

        System.out.println("\nSorted population, in descending order");
        for (GeneticProgrammingTree gpTree : population) {
            System.out.println("GP Tree Fitness: " + gpTree.getFitness());
            Utilities.printTreeNode(gpTree.getRoot());
        }
    }

    @Test
    public void testGetInitialGeneticTreePopulation() {
        System.out.println("***testGetInitialGeneticTreePopulation***");

        int size = 0;

        try {
            Properties settings = Settings.getSettings();

            String prop = settings.getProperty(Settings.PROP_POPULATION_SIZE);

            size = Integer.parseInt(prop);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not load property '" + Settings.PROP_POPULATION_SIZE + "'");
        }

        ArrayList<GeneticProgrammingTree> population = null;
        try {
            population = GeneticProgrammingTree.getGeneticTreePopulation(size);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not generate GeneticProgramming tree");
        }

        for (GeneticProgrammingTree gpTree : population) {
            Utilities.printTreeNode(gpTree.getRoot());
        }
    }

    @Test
    public void testListTreeNodes() {
        System.out.println("***testListTreeNodes***");

        int size = 0;

        try {
            Properties settings = Settings.getSettings();

            String prop = settings.getProperty(Settings.PROP_POPULATION_SIZE);

            size = Integer.parseInt(prop);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not load property '" + Settings.PROP_POPULATION_SIZE + "'");
        }

        ArrayList<GeneticProgrammingTree> population = null;
        try {
            population = GeneticProgrammingTree.getGeneticTreePopulation(size);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not generate GeneticProgramming tree");
        }

        for (GeneticProgrammingTree gpTree : population) {
            Utilities.printTreeNode(gpTree.getRoot());

            try {
                System.out.println("All Nodes: " + gpTree.getAllNodes());
                System.out.println("Operand Nodes: " + gpTree.getOperandNodes());
                System.out.println("Operator Nodes: " + gpTree.getOperatorNodes());
            } catch (Exception e) {
                e.printStackTrace();
                fail("Could not print tree nodes");
            }

        }
    }

    @Test
    public void testTreeCopy() {
        System.out.println("***testTreeCopy***");

        int size = 0;

        try {
            Properties settings = Settings.getSettings();

            String prop = settings.getProperty(Settings.PROP_POPULATION_SIZE);

            size = Integer.parseInt(prop);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not load property '" + Settings.PROP_POPULATION_SIZE + "'");
        }

        ArrayList<GeneticProgrammingTree> population = null;
        try {
            population = GeneticProgrammingTree.getGeneticTreePopulation(size);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not generate GeneticProgramming tree");
        }

        for (GeneticProgrammingTree gpTree : population) {
            GeneticProgrammingTree gpcopyTree = null;
            try {
                gpcopyTree = GeneticProgrammingTree.copy(gpTree);
            } catch (Exception e) {
                e.printStackTrace();
                fail("Could not copy tree");
            }

            System.out.println("Original - before mutation:");
            Utilities.printTreeNode(gpTree.getRoot());

            System.out.println("Copy - before mutation:");
            Utilities.printTreeNode(gpcopyTree.getRoot());

            try {
                GeneticOperators.mutate(gpcopyTree);
            } catch (Exception e) {
                e.printStackTrace();
                fail("Could not mutate tree");
            }

            System.out.println("Original - after mutation:");
            Utilities.printTreeNode(gpTree.getRoot());

            System.out.println("Copy - after mutation:");
            Utilities.printTreeNode(gpcopyTree.getRoot());
        }
    }
}
