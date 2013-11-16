package data;

import java.util.ArrayList;
import java.util.Properties;

import utilities.Settings;

public class GeneticProgrammingTree extends Tree implements Comparable<GeneticProgrammingTree>{
    
    private double fitness;
    
    private GeneticProgrammingTree(Node root, double fitness) {
        super(root);
        setFitness(fitness);
    }
    
    public double getFitness() {
        return fitness;
    }
    
    private void setFitness(double fitness) {
        this.fitness = fitness;
    }     
    
    public static GeneticProgrammingTree createGeneticProgrammingTree(ArrayList<TrainingData> trainingDataList) throws Exception {
        Properties settings = Settings.getSettings();
        
        String prop = settings.getProperty(Settings.PROP_MAX_DEPTH);
        
        int maxDepth = Integer.parseInt(prop);
    
        return createGeneticProgrammingTree(trainingDataList, maxDepth); 
    }

    private static GeneticProgrammingTree createGeneticProgrammingTree(ArrayList<TrainingData> trainingDataList, int maxDepth) {
        Tree tree = Tree.generateTree(maxDepth);
       
        double fitness = 0;
        
        for (TrainingData trainingData : trainingDataList) {
            double evalData = tree.evaluate(trainingData.inputData);
            
            if (Settings.trace()) {
                System.out.println("Evaluate tree(" + trainingData.inputData + "): " + evalData);
            }
            
            fitness = fitness + Math.abs(trainingData.outputData - evalData);
            
            if (Settings.trace()) {
                System.out.println("Tree Fitness : Math.abs(" + trainingData.outputData + " - " + evalData + ") = " + fitness);
            }
        }
        
        return new GeneticProgrammingTree(tree.getRoot(), fitness);
    }

    public int compareTo(GeneticProgrammingTree gpTree) {
        double delta  = this.fitness - gpTree.fitness;
        
        if (delta >= 0) {
            return 1;
        } else if (delta == 0) {
            return 0;
        } else {
            return -1;
        }
    }
}
