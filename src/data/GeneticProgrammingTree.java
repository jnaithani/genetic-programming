package data;

public class GeneticProgrammingTree extends Tree {
    
    private double fitness;
    
    public GeneticProgrammingTree(Node root) {
        super(root);
    }
    
    public double getFitness() {
        return fitness;
    }
    
    private void setFitness(double fitness) {
        this.fitness = fitness;
    }
}
