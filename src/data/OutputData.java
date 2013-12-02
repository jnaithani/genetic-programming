package data;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import utilities.Utilities;

import java.awt.GridLayout;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class OutputData {
    private ArrayList<GeneticProgrammingTree> fittestTreeInEachGeneration = new ArrayList<GeneticProgrammingTree>();
    private ArrayList<Integer> populationSizeInEachGeneration = new ArrayList<Integer>();
    private long startTime = 0;
    private long currentTime = 0;
    private int generationCount = 0;
    
    private JFreeChart chart = null;
    private JFrame topFrame = null;
    private JPanel mainPanel = null;
    private ChartPanel cp = null;   
    private JPanel results = null;
    private JTextArea textArea = null;
    
    XYSeries series = null;
    
    public void setStartTime(long time) {
        startTime = time;
    }
    
    public void setCurrentTime(long time) {
        currentTime = time;
    }
    
    public void incrementGenerationCount() {
        generationCount++;
    }
    
    public void resetData() {
        fittestTreeInEachGeneration = new ArrayList<GeneticProgrammingTree>();
        populationSizeInEachGeneration = new ArrayList<Integer>();
        generationCount = 0;
    }
    
    public int getGenerationCount() {
        return generationCount;
    }
    
    public void addPopulationSizeInGeneration(int size) {
        populationSizeInEachGeneration.add(Integer.valueOf(size));
    }
    
    public void addFittestTreeInGeneration(GeneticProgrammingTree tree) {
        fittestTreeInEachGeneration.add(tree);
    }
    
    public void displayResults() throws Exception {
        printSeperatorLine();
        printResults();
        updateDashboard();
        printSeperatorLine();
    }
    
    public void displayPopulation(ArrayList<GeneticProgrammingTree> population) {
        int index = 0;
        for (GeneticProgrammingTree gpTree : population) {
            System.out.print("population[" + index + "]         = ");
            gpTree.inOrderPrint();
            System.out.println("population[" + index + "].fitness = " + gpTree.getFitness());
            ++index;
        }
    }

    private void printSeperatorLine() {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    }
    
    public void displayFinalResults() throws Exception {
        printSeperatorLine();
        System.out.println("----------------------------------------------------------------*** Final Results ***-----------------------------------------------------------------------------");
        printResults();
        updateDashboard();
        System.out.println("");
        if (fittestTreeInEachGeneration.get(generationCount - 1).getRoot().depth() < 9) {
            Utilities.printTreeNode(fittestTreeInEachGeneration.get(generationCount - 1).getRoot());
        }
        generateBestFitnessGenerationChart();
        printSeperatorLine();
        printSeperatorLine();
    }
    
    private void generateBestFitnessGenerationChart() throws Exception {
        XYSeries series = new XYSeries("Best Fitness/Generation");
        
        int gen = 0;
        for (GeneticProgrammingTree gpTree : fittestTreeInEachGeneration) {
            series.add(gen, gpTree.getFitness());
            ++gen;
        }
        
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        
        JFreeChart chart = ChartFactory.createXYLineChart(
            "Best Fitness/Generation", // Title
            "Generation",              // x-axis Label
            "Best Fitness",            // y-axis Label
            dataset,                   // Data set
            PlotOrientation.VERTICAL,  // Plot Orientation
            true,                      // Show Legend
            true,                      // Use tool tips
            false                      // Generate URLs
        );

        ChartUtilities.saveChartAsJPEG(new File("bestfitness_generation.jpg"), chart, 1500, 900);
    }
    
    public void recordInitialPopulationFitness(ArrayList<GeneticProgrammingTree> population) throws Exception {
        String fileName = "initial_population_fitness";
        recordFitnessOfPopulation(population, "Initial Population/Fitness", fileName);
    }
    
    public void recordFinalPopulationFitness(ArrayList<GeneticProgrammingTree> population) throws Exception {
        String fileName = "final_population_fitness";
        recordFitnessOfPopulation(population, "Final Population/Fitness", fileName);
    }
    
    private void recordFitnessOfPopulation(ArrayList<GeneticProgrammingTree> population, String title, String fileName) throws Exception {
        XYSeries series = new XYSeries(title);
        
        int t = 0;
        for (GeneticProgrammingTree gpTree : population) {
            series.add(t, gpTree.getFitness());
            ++t;
        }
        
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        
        JFreeChart chart = ChartFactory.createXYLineChart(
            title,                    
            "Tree",                    
            "Fitness",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );

        ChartUtilities.saveChartAsJPEG(new File(fileName + ".jpg"), chart, 1500, 900);
    }
    
    public void recordXYGraph() throws Exception{
        XYSeries series = new XYSeries("x/f(x)");
            
        for (TrainingData trainingData : TrainingData.getTrainingData()) {
            series.add(trainingData.inputData, fittestTreeInEachGeneration.get(generationCount - 1).evaluate(trainingData.inputData));
        }
        
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        
        JFreeChart chart = ChartFactory.createXYLineChart(
            "x/f(x)",                    
            "x",                    
            "y",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );

        ChartUtilities.saveChartAsJPEG(new File("xy_graph.jpg"), chart, 1500, 900);       
    }
    
    public void createDashboard() throws Exception{
        series = new XYSeries("f(x)");
            
        for (TrainingData trainingData : TrainingData.getTrainingData()) {
            series.add(trainingData.inputData, fittestTreeInEachGeneration.get(generationCount - 1).evaluate(trainingData.inputData));
        }
        
        createAndShowDashboard(); 
    }

    private void createAndShowDashboard() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);
        
        JFrame.setDefaultLookAndFeelDecorated(true);
        topFrame = new JFrame("\t\t\tGenetic Programming System");

        mainPanel = new JPanel();
        GridLayout layout = new GridLayout(0,1);
        mainPanel.setLayout(layout);
        
        results = new JPanel();
        
        textArea = new JTextArea(9, 80);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);
        results.add(scrollPane);
        
        topFrame.setSize(1200, 800);
        topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        topFrame.setVisible(true);

         chart = ChartFactory.createXYLineChart(
            "f(x)",                    
            "x",                    
            "y",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );
        
        cp = new ChartPanel(chart);
        mainPanel.add(cp);
        mainPanel.add(results);
    
        topFrame.getContentPane().add(mainPanel);
    }
    
    public void updateDashboard() throws Exception {
        if (topFrame == null) {
            createDashboard();
        }
        
        for (TrainingData trainingData : TrainingData.getTrainingData()) {
            series.update(trainingData.inputData, fittestTreeInEachGeneration.get(generationCount - 1).evaluate(trainingData.inputData));
        }
        
        Date start = new Date(startTime); 
        Date end = new Date(currentTime);

         // This is to format the your current date to the desired format
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        
        String startString = sdf.format(start);
        String endString = sdf.format(end);
        
        StringBuffer sb = new StringBuffer();
        sb.append("Start Time\t\t\t: " + startString + "\n");
        sb.append("Current Time\t\t\t: " + endString + "\n");
        sb.append("Elapsed seconds\t\t: " + (currentTime - startTime) + " milliseconds" + "\n");
        
        sb.append("Current generation count\t\t: " + generationCount + "\n");
        sb.append("Current generation population size\t: " + populationSizeInEachGeneration.get(generationCount - 1) + "\n");  
        
        sb.append("Fittest Solution\t\t\t: " + fittestTreeInEachGeneration.get(generationCount - 1).getExpression() + "\n");
        sb.append("Fittest Soluton depth\t\t: " + fittestTreeInEachGeneration.get(generationCount - 1).depth() + "\n");
        sb.append("Fitness\t\t\t: " + fittestTreeInEachGeneration.get(generationCount - 1).getFitness() + "\n");
        textArea.setText(sb.toString());
    }
    
    private void printResults() throws Exception {
        Date start = new Date(startTime); 
        Date end = new Date(currentTime);

         // This is to format the your current date to the desired format
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        
        String startString = sdf.format(start);
        String endString = sdf.format(end);
        System.out.println("Start Time                          : " + startString);
        System.out.println("Current Time                        : " + endString);
        System.out.println("Elapsed seconds                     : " + (currentTime - startTime) + " milliseconds");
        System.out.println("Current generation count            : " + generationCount);
        System.out.println("Current generation population size  : " + populationSizeInEachGeneration.get(generationCount - 1));
        System.out.print("Fittest Solution                    : ");
        fittestTreeInEachGeneration.get(generationCount - 1).inOrderPrint();
        System.out.println("Fittest Solution (trimmed)          : " + fittestTreeInEachGeneration.get(generationCount - 1).getExpression());
        System.out.println("Fittest Soluton depth               : " + fittestTreeInEachGeneration.get(generationCount - 1).depth());
        System.out.println("Fitness                             : " + fittestTreeInEachGeneration.get(generationCount - 1).getFitness());        
    }        
}
