package utilities;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Random;

import data.GeneticProgrammingTree;
import data.Node;
import data.Tree;

public class GeneticOperators {
    
    public static ArrayList<GeneticProgrammingTree> selection(ArrayList<GeneticProgrammingTree> population) throws Exception {
        int newPopulationSize = getNewPopulationSize(population.size());
                
        ArrayList<GeneticProgrammingTree> newPopulation = new ArrayList<GeneticProgrammingTree>();
       
        int index = 0;
        while (newPopulation.size() < newPopulationSize) {
            newPopulation.add(population.get(index));
            index++;
        }
        
        return newPopulation;
    }

    private static int getNewPopulationSize(int populationSize) throws Exception {
        double survivalProbability = Settings.getSurvivalProbability();
        
        int newPopulationSize = (int) Math.ceil(survivalProbability * populationSize);
        
        return newPopulationSize;
    }
    
    public static void crossover(ArrayList<GeneticProgrammingTree> population) throws Exception {
        int numOfPairsForCrossover = getNumberOfPairsForCrossover(population.size());
        
        for (int i = 0; i < numOfPairsForCrossover; i = i + 2) {
            GeneticOperators.crossover(population.get(i), population.get(i + 1));
        }
    }
    
    private static int getNumberOfPairsForCrossover(int populationSize) throws Exception {
        double crossoverProbablity = Settings.getCrossoverProbability();
        
        int numberOfPairsForCrossover = (int) Math.floor((crossoverProbablity * populationSize)/2);
        
        return numberOfPairsForCrossover;
    }
    
    public static void crossover(Tree parent1, Tree parent2) throws Exception {
        ArrayList<Node> p1Nodes = parent1.getAllNodes();
        ArrayList<Node> p2Nodes = parent2.getAllNodes();
        
        Random random = new Random();
        int indexp1 = random.nextInt(p1Nodes.size());
        int indexp2 = random.nextInt(p2Nodes.size());
        
        if (Settings.trace()) {
            System.out.println("Trace: Crossover node index for first tree: " + indexp1);
            System.out.println("Trace: Crossover node index for first tree: " + indexp2);
        }
        
        Node n1 = p1Nodes.get(indexp1);
        Node n2 = p2Nodes.get(indexp2);
        
        Node.swap(n1, n2);
    }
    
    public static void mutate(Tree tree) throws Exception {
        if (tree.size() > 1) {
            ArrayList<Node> operandNodes = tree.getOperandNodes();
            ArrayList<Node> operatorNodes = tree.getOperatorNodes();
            
            if (Settings.trace()) {
                System.out.println("Operand Nodes: " + operandNodes);
                System.out.println("Operator Nodes: " + operatorNodes);
            }
            
            double random = Math.random();
            if (random <= 0.5) {
                mutateOperatorNode(operatorNodes);
            } else {
                mutateOperandNode(operandNodes);
            }
        } else {
            ArrayList<Node> operandNodes = tree.getOperandNodes();
            mutateOperandNode(operandNodes);
        }
    }
    
    public static void mutateOperatorNode(ArrayList<Node> nodes) throws Exception {
        Random random = new Random();
        int nodeIndex = random.nextInt(nodes.size());
        
        Node node = NodeFactory.getFunction();
        
        Node mutationNode = nodes.get(nodeIndex);
        
        mutationNode.setDataItem(node.getDataItem());
    }
    
    public static void mutateOperandNode(ArrayList<Node> nodes) throws Exception {
        Random random = new Random();
        int nodeIndex = random.nextInt(nodes.size());
        
        Node node = NodeFactory.getTerminal();
        
        Node mutationNode = nodes.get(nodeIndex);
        
        mutationNode.setDataItem(node.getDataItem());
    }
}
