
/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		get();
	}

	private void get() {

		int num;
		int new_num;
		int count = 0;
		num = readInt("Enter the number : ");
		while (true) {
			if (num % 2 == 0 && num != 1) {
				new_num = num / 2; // with this method given number is divided by 2
				println(num + " is even so I take half : " + new_num);
				num = new_num; // with this method new_num gets the new value of the divided num
			}
			if (num % 2 != 0 && num != 1) {
				new_num = 3 * num + 1; // this method multiplies the given number by 3 and adds 1
				println(num + " is odd so I do 3n+1 : " + new_num);
				num = new_num; // with this method new_num gets the new value of the divided num
			}
			if (num == 1) {
				break;
				// with this method when the number gets the value 1 program stops
			}
			count = count + 1; // this method count the steps after each cycle
		}
		println( count ); // this method shows the number of steps on the
																		// window
	}
}
