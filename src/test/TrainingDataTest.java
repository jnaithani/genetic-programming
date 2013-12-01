package test;

import data.TrainingData;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TrainingDataTest {

    @Test
    public void testLoadTrainingData() throws Exception{
        System.out.println("***testLoadTrainingData***");
        
        int minXValue = -10, testDataSize = 20;
        TrainingData.generateInitialTrainingData(minXValue, testDataSize);
        ArrayList<TrainingData> trainingData = TrainingData.getTrainingData();

        assertEquals(20, trainingData.size());
    }
}
