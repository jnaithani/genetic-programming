package utilities;

import java.util.ArrayList;
import java.util.Random;

import data.Node;
import data.Tree;

public class GeneticOperators {
    
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

}
