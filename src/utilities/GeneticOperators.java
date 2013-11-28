package utilities;

import data.GeneticProgrammingTree;
import data.Node;
import data.TrainingData;
import data.Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GeneticOperators {
    
    public static ArrayList<GeneticProgrammingTree> selection(ArrayList<GeneticProgrammingTree> population) throws Exception {
        ArrayList<GeneticProgrammingTree> newPopulation = new ArrayList<GeneticProgrammingTree>(Settings.getPopulationSize());
        
        int numberOfSurvivors = getNumberOfSurvivors();
       
        int index = 0;
        while (index < numberOfSurvivors) {
            newPopulation.add(GeneticProgrammingTree.copy(population.get(index)));
            
            if (Settings.trace()) {
                System.out.print("[Trace] newPopulation[" + newPopulation.size() + "]         = ");
                newPopulation.get(newPopulation.size() - 1).inOrderPrint();
                System.out.println("[Trace] newPopulation[" + newPopulation.size() + "].fitness = " + newPopulation.get(newPopulation.size() - 1).getFitness());
            }
            
            ++index;
        }
        
        return newPopulation;
    }

    private static int getNumberOfSurvivors() throws Exception {
        double survivalProbability = Settings.getSurvivalProbability();
        double populationSize = Settings.getPopulationSize();
        
        int numberOfSurvivors = (int) Math.ceil(survivalProbability * populationSize);
        
        if (Settings.trace()) {
            System.out.println("[Trace] New Generation Population Size: " + numberOfSurvivors);
        }

        return numberOfSurvivors;
    }
    
    public static void mutateTrees(ArrayList<GeneticProgrammingTree> trees) {

        int treeSize = trees.size();
        if (treeSize < 0)
            return;

        try {
            Random randomGenerator = new Random();
            int mutateNum = (int) Math.ceil(Settings.getMutationProbability() * treeSize);

            for (int i = 0; i < mutateNum; i++) {

                //grab a single random tree
                GeneticProgrammingTree tree = trees.get(randomGenerator.nextInt(mutateNum));

                //mutate tree
                mutate(tree);

                //evaluate fitness with training data
//                GeneticProgrammingTree.updateFitness(tree, TrainingData.getTrainingData());

//                Collections.sort(trees);
            }

        } catch (Exception e) {
            System.out.println("Failed in Mutating Trees");
            e.printStackTrace();
        }

    }

    public static ArrayList<GeneticProgrammingTree> crossoverTrees(ArrayList<GeneticProgrammingTree> population) throws Exception {
        ArrayList<GeneticProgrammingTree> children = new ArrayList<GeneticProgrammingTree>();
        int numOfPairsForCrossover = getNumberOfPairsForCrossover(population.size());
        
        for (int i = 0; i < numOfPairsForCrossover * 2; i = i + 2) {
            GeneticOperators.crossover(population.get(i), population.get(i + 1));
            children.add(population.get(i));
            children.add(population.get(i + 1));
        }
        
        return children;
    }
    
    private static int getNumberOfPairsForCrossover(int populationSize) throws Exception {
        double crossoverProbablity = Settings.getCrossoverProbability();
        
        int numberOfPairsForCrossover = (int) Math.floor((crossoverProbablity * populationSize)/2);
        
        return numberOfPairsForCrossover;
    }
    
    public static void crossover(Tree t1, Tree t2) throws Exception {
        ArrayList<Node> p1Nodes = t1.getAllNodes();
        ArrayList<Node> p2Nodes = t2.getAllNodes();
        
        Random random = new Random();
        int indexp1 = random.nextInt(p1Nodes.size());
        int indexp2 = random.nextInt(p2Nodes.size());
        
        if (Settings.trace()) {
            System.out.println("Trace: Crossover node index for first tree : " + indexp1);
            System.out.println("Trace: Crossover node index for second tree: " + indexp2);
        }
        
        Node n1 = p1Nodes.get(indexp1);
        Node n2 = p2Nodes.get(indexp2);
        
        if (Settings.trace()) {
            System.out.println("Trace: Crossover point node value for first tree : " + n1);
            System.out.println("Trace: Crossover point node value for second tree: " + n2);
        }
        
        Tree.swap(t1, n1, t2, n2);
    }
    
    public static void mutate(Tree tree) throws Exception {
        if (tree.depth() > 1) {
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
