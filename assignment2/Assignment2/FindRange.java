
/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {
	private static final int finish = 0;

	private int num;
	private int max;
	private int min;

	public void run() {
		getRange();
		// this method shows the min and max numbers
	}

	private void getRange() {
		println("This program shows MAX and MIN of the given numbers : ");
		num = readInt("Enter number: "); // this method asks to print the number
		min = num;
		max = num;
		if (num == finish) {
			println("This number is the end of the program "); // this method writes on he window that 0 is the end of
																// the program
		} else {
			other();
		}

		println("Max number is " + max); // this method shows the max number of the given numbers
		println("Min number is " + min);// this method shows the min number of the given numbers
	}

	private void other() {
		while (true) {
			num = readInt("Enter number: ");
			// this method compares the numbers to each other and in the end it gives just
			// min and max numbers
			if (num == finish)
				break;
			if (num > max) {
				max = num;
			}
			if (num < min) {
				min = num;
			}
		}
	}
}
