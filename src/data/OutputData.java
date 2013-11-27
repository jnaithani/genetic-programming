package data;

import java.util.ArrayList;
import java.util.Date;

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
    
    public void addPopulationSizeInGeneration(int size) {
        populationSizeInEachGeneration.add(Integer.valueOf(size));
    }
    
    public void addFittestTreeInGeneration(GeneticProgrammingTree tree) {
        fittestTreeInEachGeneration.add(tree);
    }
    
    public void displayResults() {
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        System.out.println("Elapsed seconds  : " + ((new Date()).getTime() - startTime));
        System.out.println("Generation count : " + generationCount);
        System.out.print("Fittest Solution : ");
        fittestTreeInEachGeneration.get(generationCount - 1).inOrderPrint();
        System.out.println("Fitness          : " + fittestTreeInEachGeneration.get(generationCount - 1).getFitness());
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
    }
}
