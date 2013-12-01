package utilities;

import data.GeneticProgrammingTree;
import data.Node;
import data.Tree;

import java.util.ArrayList;
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
            int mutateNum = getNumberOfTreesForMutation(treeSize);

            for (int i = 0; i < mutateNum; i++) {

                //grab a single random tree
                int i1 = randomGenerator.nextInt(trees.size());
                GeneticProgrammingTree tree = trees.get(i1);

                if (Settings.trace()) {
                    System.out.println("[Trace] Before Mutation - Tree[" + i1 + "]");
                    trees.get(i1).inOrderPrint();
                }
                
                mutate(tree);
                
                if (Settings.trace()) {
                    System.out.println("After Mutation - Tree[" + i1 + "]");
                    trees.get(i1).inOrderPrint();
                }
            }

        } catch (Exception e) {
            System.out.println("Failed in Mutating Trees");
            e.printStackTrace();
        }

    }

    private static int getNumberOfTreesForMutation(int treeSize) throws Exception {
        return (int) Math.ceil(Settings.getMutationProbability() * treeSize);
    }

    public static ArrayList<GeneticProgrammingTree> crossoverTrees(ArrayList<GeneticProgrammingTree> population) throws Exception {
        ArrayList<GeneticProgrammingTree> children = new ArrayList<GeneticProgrammingTree>();
        int numOfPairsForCrossover = getNumberOfPairsForCrossover(population.size());
        
        Random randomGenerator = new Random();
        
        for (int i = 0; i < numOfPairsForCrossover; ++i) {
            int i1 = 0, i2 = 0;
            while (i1 == i2) {
                i1 = randomGenerator.nextInt(population.size());
                i2 = randomGenerator.nextInt(population.size());
            }
            
            if (Settings.trace()) {
                System.out.println("Before Crossover - Tree[" + i1 + "]");
                population.get(i1).inOrderPrint();
                System.out.println("Before Crossover - Tree[" + i2 + "]");
                population.get(i2).inOrderPrint();
            }
            
            GeneticProgrammingTree treeOne = GeneticProgrammingTree.copy(population.get(i1));
            GeneticProgrammingTree treeTwo = GeneticProgrammingTree.copy(population.get(i2));

            if (treeOne.depth() != 1 && treeTwo.depth() != 1) {
                GeneticOperators.crossover(treeOne, treeTwo);
            }
            
            if (Settings.trace()) {
                System.out.println("Afer Crossover - copy of Tree[" + i1 + "]");
                treeOne.inOrderPrint();
                System.out.println("After Crossover - copy of Tree[" + i2 + "]");
                treeTwo.inOrderPrint();
            }
            
            if (Settings.isMaxDepthLimitSet()) {
                if (treeOne.depth() > Settings.maxDepthLimit()) {     
                    treeOne = getCopyOfRandomParent(population.get(i1), population.get(i2));
                }
                
                if (treeTwo.depth() > Settings.maxDepthLimit()) {
                    treeTwo = getCopyOfRandomParent(population.get(i1), population.get(i2));
                }
            }
            
            children.add(treeOne);
            children.add(treeTwo);
        }
        
        return children;
    }

    private static GeneticProgrammingTree getCopyOfRandomParent(GeneticProgrammingTree p1, GeneticProgrammingTree p2) throws Exception {
        double probability = Math.random();

        if (probability < 0.5) {
            return GeneticProgrammingTree.copy(p1);
        }
        else {
            return GeneticProgrammingTree.copy(p2);
        }
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
        int indexp1 = 0, indexp2 = 0;
        boolean rootNodes = true;
        while (rootNodes) {
            indexp1 = random.nextInt(p1Nodes.size());
            indexp2 = random.nextInt(p2Nodes.size());
            
            rootNodes = areRootNodes(p1Nodes, p2Nodes, indexp1, indexp2);
        }

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

    public static boolean areRootNodes(ArrayList<Node> p1Nodes,ArrayList<Node> p2Nodes, int indexp1, int indexp2) {
        boolean areRootNodes = true;
        
        if ((p1Nodes.get(indexp1).getParent() != null) || p2Nodes.get(indexp2).getParent() != null) {
            areRootNodes = false;
        }
        
        return areRootNodes;
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
        
        Node mutationNode = nodes.get(nodeIndex);

        Node node = null;
        do {
            node = NodeFactory.getFunction();;
        } while (node.getDataItem() == mutationNode.getDataItem());
        
        mutationNode.setDataItem(node.getDataItem());
    }
    
    public static void mutateOperandNode(ArrayList<Node> nodes) throws Exception {
        Random random = new Random();
        int nodeIndex = random.nextInt(nodes.size());
        
        Node mutationNode = nodes.get(nodeIndex);
        
        Node node = null;
        do {
            node = NodeFactory.getTerminal();
        } while (node.getDataItem() == mutationNode.getDataItem());
        
        mutationNode.setDataItem(node.getDataItem());
    }
}
