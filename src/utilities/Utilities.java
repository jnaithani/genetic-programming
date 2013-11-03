package utilities;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Random;

public class Utilities {

    private String strLine = "";

    /**
     * Gets a random number
     *
     * @param minimum
     * @param maximum
     * @return
     */
    public static int getRandomNumber(int minimum, int maximum) {

        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt(maximum - minimum + 1) + minimum;
        return randomNum;


    }

    /**
     * Reads a file using the filename and will return
     *
     * @param fileName
     * @return
     */
    public static HashMap<String, String> readFileGetLine(String fileName) {

        HashMap<String, String> lines = new HashMap<String, String>();
        try {
            FileInputStream fstream = new FileInputStream(fileName);
            // Get the object of DataInputStream
            DataInputStream in = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String strLine;


            //read line by line and close the Data stream

            //Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // Print the content on the console
                String[] strArr = strLine.split(",");
                String key = strArr[0], value = strArr[1];

                lines.put(key, value);

            }
            //Close the input stream
            in.close();


        } catch (Exception e) {
            e.printStackTrace();
        }


        return lines;
    }

}
