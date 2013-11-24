package test;

import org.junit.Test;
import utilities.Utilities;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class GPUtilities {


    @Test
    public void testFileReader() throws Exception {
        String fileName = "src/training.txt";
        HashMap<String,String> lines = Utilities.readFileGetLine(fileName);

        assertEquals(3, lines.size());

    }

    @Test
    public void testGetRandomNumber() {

        int minimum = 0;
        int maximum = 1000000;
        for (int i = 0; i < 1000; i++) {

            int temp = Utilities.getRandomNumber(minimum, maximum);

            if ((temp < minimum) || (temp > maximum)) {
                fail("Failed to meet in between"
                        + minimum
                        + " and: "
                        + maximum + ". Number returned: "
                        + temp);
            }
        }

    }

}
