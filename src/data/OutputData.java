package data;

import java.util.ArrayList;

import utilities.Utilities;

public class OutputData {
    private ArrayList<GeneticProgrammingTree> fittestTreeInEachGeneration = new ArrayList<GeneticProgrammingTree>();
    private ArrayList<Integer> populationSizeInEachGeneration = new ArrayList<Integer>();
    private long startTime = 0;
    private long currentTime = 0;
    private int generationCount = 0;
    
    public void setStartTime(long time) {
        startTime = time;
    }
    
    public void setCurrentTime(long time) {
        currentTime = time;
    }
    
    public void incrementGenerationCount() {
        generationCount++;
    }
    
    public int getGenerationCount() {
        return generationCount;
    }
    
    public void addPopulationSizeInGeneration(int size) {
        populationSizeInEachGeneration.add(Integer.valueOf(size));
    }
    
    public void addFittestTreeInGeneration(GeneticProgrammingTree tree) {
        fittestTreeInEachGeneration.add(tree);
    }
    
    public void displayResults() {
        printSeperatorLine();
        printResults();
        printSeperatorLine();
    }
    
    public void displayPopulation(ArrayList<GeneticProgrammingTree> population) {
        int index = 0;
        for (GeneticProgrammingTree gpTree : population) {
            System.out.print("population[" + index + "]         = ");
            gpTree.inOrderPrint();
            System.out.println("population[" + index + "].fitness = " + gpTree.getFitness());
            ++index;
        }
    }

    private void printSeperatorLine() {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    public void displayFinalResults() {
        printSeperatorLine();
        System.out.println("----------------------------------------------------------------*** Final Results ***-----------------------------------------------------------------------------");
        printResults();
        System.out.println("");
        Utilities.printTreeNode(fittestTreeInEachGeneration.get(generationCount - 1).getRoot());
        printSeperatorLine();
        printSeperatorLine();
    }

    private void printResults() {
        System.out.println("Elapsed seconds                     : " + (currentTime - startTime));
        System.out.println("Current generation count            : " + generationCount);
        System.out.println("Current generation population size  : " + populationSizeInEachGeneration.get(generationCount - 1));
        System.out.print("Fittest Solution                    : ");
        fittestTreeInEachGeneration.get(generationCount - 1).inOrderPrint();
        System.out.println("Fitness                             : " + fittestTreeInEachGeneration.get(generationCount - 1).getFitness());
    }
}
