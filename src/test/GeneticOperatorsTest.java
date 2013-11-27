package test;

import data.GeneticProgrammingTree;
import org.junit.Test;
import utilities.GeneticOperators;
import utilities.Settings;
import utilities.Utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;

import static org.junit.Assert.fail;

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
    public void testMutation() {
        System.out.println("***testMutation***");

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
            try {
                System.out.println("Before mutation:");
                Utilities.printTreeNode(gpTree.getRoot());

                GeneticOperators.mutate(gpTree);

                System.out.println("After mutation:");
                Utilities.printTreeNode(gpTree.getRoot());
            } catch (Exception e) {
                e.printStackTrace();
                fail("Could not perform mutation");
            }
        }
    }

    @Test
    public void testSelection() {
        System.out.println("***testSelection***");

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

        ArrayList<GeneticProgrammingTree> newPopulation = null;

        System.out.println("Before selection:");
        for (GeneticProgrammingTree gpTree : population) {
            try {
                System.out.println("Fitness: " + gpTree.getFitness());
            } catch (Exception e) {
                e.printStackTrace();
                fail("Could not perform selection");
            }
        }

        try {
            newPopulation = GeneticOperators.selection(population);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Could not perform selection");
        }

        System.out.println("After selection:");
        for (GeneticProgrammingTree gpTree : newPopulation) {
            try {
                System.out.println("Fitness: " + gpTree.getFitness());
            } catch (Exception e) {
                e.printStackTrace();
                fail("Could not perform selection");
            }
        }
    }

    @Test
    public void testMutateTrees() throws Exception {
        Properties settings = Settings.getSettings();
        String prop = settings.getProperty(Settings.PROP_POPULATION_SIZE);

        int size = Integer.parseInt(prop);
        ArrayList<GeneticProgrammingTree> population = GeneticProgrammingTree.getGeneticTreePopulation(size);

        GeneticOperators.mutateTrees(population);
    }
}
