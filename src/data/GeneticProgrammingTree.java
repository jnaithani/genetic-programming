package data;

public class GeneticProgrammingTree {
    
    private BinaryTree tree;
    private double fitness;
    
    public GeneticProgrammingTree(BinaryTree tree, double fitness) {
        this.tree = tree;
        this.fitness = fitness;
    }
    
    public double getFitness() {
        return fitness;
    }
    
    public BinaryTree getTree() {
        return tree;
    }
    
    private void setFitness(double fitness) {
        this.fitness = fitness;
    }
}
