package test;

import data.GeneticProgrammingTree;
import data.Node;
import org.junit.Test;
import utilities.GeneticOperators;
import utilities.Settings;
import utilities.Utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import static org.junit.Assert.*;

public class GeneticOperatorsTest {

    @Test
    public void testCrossover() {
        System.out.println("***testCrossover***");

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

        for (int i = 0; i < size - 1; i = i + 2) {
            try {
                System.out.println("crossover(" + i + "," + (i + 1) + ")");
                System.out.println("Before crossover:");
                Utilities.printTreeNode(population.get(i).getRoot());
                Utilities.printTreeNode(population.get(i + 1).getRoot());

                GeneticOperators.crossover(population.get(i), population.get(i + 1));

                System.out.println("After crossover:");
                Utilities.printTreeNode(population.get(i).getRoot());
                Utilities.printTreeNode(population.get(i + 1).getRoot());
            } catch (Exception e) {
                e.printStackTrace();
                fail("Could not perform crossover");
            }
        }
    }

    @Test
    public void testSelection() throws Exception {
        System.out.println("***testSelection***");

        Properties settings = Settings.getSettings();
        String prop = settings.getProperty(Settings.PROP_POPULATION_SIZE);
        int size = Integer.parseInt(prop);

        ArrayList<GeneticProgrammingTree> population = GeneticProgrammingTree.getGeneticTreePopulation(size);
        ArrayList<GeneticProgrammingTree> newPopulation = GeneticOperators.selection(population);
        Collections.sort(population);



        //make sure sizes of original population and after selection are not the same
        double selectionProbability = Settings.getSurvivalProbability();
        int customPopSize = 340;

        int numberOfSurvivors = (int) Math.ceil(selectionProbability * customPopSize);


        assertEquals(68, numberOfSurvivors); // test to make sure probability and number of trees moving forward
        assertFalse(population.size() == newPopulation.size());

    }

    @Test
    public void testMutate() throws Exception {
        ArrayList<GeneticProgrammingTree> population = null;
        try {
            int size = 0;
            Properties settings = Settings.getSettings();
            String prop = settings.getProperty(Settings.PROP_POPULATION_SIZE);
            size = Integer.parseInt(prop);
            population = GeneticProgrammingTree.getGeneticTreePopulation(size);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not generate GeneticProgramming tree");
        }


        GeneticProgrammingTree singleTree = population.get(1); //get the first tree from the population
        ArrayList<String> beforeMutationLst = Node.postOrderItems(singleTree.getRoot());
        String postOrderStr = Utilities.convertArrayListToString(beforeMutationLst);
        int originalDepth = singleTree.depth();

        GeneticOperators.mutate(singleTree);

        ArrayList<String> afterMutationLst = Node.postOrderItems(singleTree.getRoot());
        String postOrderStrAfter = Utilities.convertArrayListToString(afterMutationLst);

        assertEquals(originalDepth, singleTree.depth());
        assertNotSame(postOrderStrAfter, postOrderStr);
    }
}
