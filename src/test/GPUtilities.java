package test;

import static org.junit.Assert.*;

import org.junit.Test;

import utilities.Utilities;

public class GPUtilities {

	@Test
	public void test() {
		assertEquals("works", "works");

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
