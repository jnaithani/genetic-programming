package utilities;

import java.util.Random;

public class Utilities {

	public Utilities() {
		
		
	}
	/**
	 * Gets a random number
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

}
