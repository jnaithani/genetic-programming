package data;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Properties;

import utilities.Settings;

public class GeneticProgrammingTree extends Tree {
    
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
            fitness = fitness + Math.abs(trainingData.outputData - evalData);
            
            if (Settings.debug()) {
                System.out.println("Fitness : " + fitness);
            }
        }
        
        return new GeneticProgrammingTree(tree.getRoot(), fitness);
    }
}
