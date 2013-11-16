package utilities;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import data.Node;

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
	 * @param operator
	 * @param a
	 * @param b
	 * @return
	 */
	public static double performOperation(char operator, double a, double b) {
		double yValue = 0.0;

		switch (operator) {
		case '+':
			yValue = a + b;
			break;
		case '-':
			yValue = a - b;
			break;
		case '/':
			yValue = a / b;
			break;
		case '*':
			yValue = a * b;
		default:
			break;
		}

		return yValue;
	}

	/**
	 * 
	 * @param root
	 */
	public static void printTreeNode(Node root) {

		int maxLevel = getMaxNodeLevel(root);
		printTreeNodeInternal(Collections.singletonList(root), 1, maxLevel);
	}

	/**
	 * 
	 * @param root
	 * @param maxLevel
	 */
	private static void printTreeNodeInternal(List<Node> nodes, int level, int maxLevel) {
	       if (nodes.isEmpty() || isAllElementsNull(nodes))
	            return;

		int floor = maxLevel - level;
		int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
		int firstSpaces = (int) Math.pow(2, (floor)) - 1;
		int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

		printWhitespaces(firstSpaces);

		List<Node> newNodes = new ArrayList<Node>();


		for (Node node : nodes) {
			if (node != null) {
				System.out.print(node.getDataItem());
				newNodes.add(node.getLeftChild());
				newNodes.add(node.getRightChild());
			} else {
				newNodes.add(null);
				newNodes.add(null);
				System.out.print(" ");
			}

			printWhitespaces(betweenSpaces);
		}
		System.out.println("");
		

		for (int i = 1; i <= endgeLines; i++) {
			for (int j = 0; j < nodes.size(); j++) {
				printWhitespaces(firstSpaces - i);
				if (nodes.get(j) == null) {
					printWhitespaces(endgeLines + endgeLines + i
							+ 1);
					continue;
				}

				if (nodes.get(j).getLeftChild() != null)
					System.out.print("/");
				else
					printWhitespaces(1);

				printWhitespaces(i + i - 1);

				if (nodes.get(j).getRightChild() != null)
					System.out.print("\\");
				else
					printWhitespaces(1);

				printWhitespaces(endgeLines + endgeLines - i);
			}

			System.out.println("");
		}

		printTreeNodeInternal(newNodes, level + 1, maxLevel);
	}

	/**
	 * 
	 * @param root
	 * @return
	 */

	public static int getMaxNodeLevel(Node root) {
		if (root == null)
			return 0;

		return Math.max(getMaxNodeLevel(root.getLeftChild()),
				getMaxNodeLevel(root.getRightChild())) + 1;
	}

	/**
	 * 
	 * @param count
	 */
	private static void printWhitespaces(int count) {
		for (int i = 0; i < count; i++)
			System.out.print(" ");
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

			// read line by line and close the Data stream

			// Read File Line By Line
			while ((strLine = br.readLine()) != null) {
				// Print the content on the console
				String[] strArr = strLine.split(",");
				String key = strArr[0], value = strArr[1];

				lines.put(key, value);

			}
			// Close the input stream
			in.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return lines;
	}

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }
}
