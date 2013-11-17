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
    
    public static ArrayList<GeneticProgrammingTree> getGeneticTreePopulation(int size) throws Exception {
        ArrayList<GeneticProgrammingTree> population = new ArrayList<GeneticProgrammingTree>(size);
        boolean singletonExists = false;
        int i = 0;
        while (i < size) {
            GeneticProgrammingTree gpTree = GeneticProgrammingTree.createGeneticProgrammingTree(TrainingData.getTrainingData()); 
            
            if (gpTree.size() == 1) {
                if (singletonExists) {
                    continue;
                } else {
                    if (gpTree.equals(new OperandNode(OperandNode.OPERAND_X))) {
                        population.add(gpTree);
                        i++;
                    }
                }
            } else {
                if (gpTree.exists(new OperandNode(OperandNode.OPERAND_X))) {
                    population.add(gpTree);
                    i++;
                }
                else {
                    continue;
                }
            }
        }
        
        return population;
    }
    
    public static GeneticProgrammingTree createGeneticProgrammingTree(ArrayList<TrainingData> trainingDataList) throws Exception {
        Properties settings = Settings.getSettings();
        
        String prop = settings.getProperty(Settings.PROP_MAX_DEPTH);
        
        int maxDepth = Integer.parseInt(prop);
    
        return createGeneticProgrammingTree(trainingDataList, maxDepth); 
    }

    private static GeneticProgrammingTree createGeneticProgrammingTree(ArrayList<TrainingData> trainingDataList, int maxDepth) throws Exception {
        Tree tree = generateInitialTree(maxDepth);
       
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

    public static Tree generateInitialTree(int maxDepth) throws Exception {
        Tree tree = null;
        do {
            tree = Tree.generateTree(maxDepth);
        } while (Double.isNaN(tree.evaluate(0)) || Double.isInfinite(tree.evaluate(0)));
        
        if (Settings.trace()) {
            System.out.println("Evaluate for x=0: " + tree.evaluate(0));
        }
        
        return tree;
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
