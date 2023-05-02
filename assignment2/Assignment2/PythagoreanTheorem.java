
/*
 * File: PythagoreanTheorem.java
 * Name: 
 * Section Leader: 
 * -----------------------------
 * This file is the starter file for the PythagoreanTheorem problem.
 */

import acm.program.*;

public class PythagoreanTheorem extends ConsoleProgram {
	public void run() {
		getHip(); // THIS METHOD SHOWS THE CALCULATED HYPOTENUS
	}

	private void getHip() {
		int Kathetus1 = readInt("Please type the length of the first Kathetus :  ");// this method asks to give the
																					// number for the first KAT.
		int Kathetus2 = readInt("Please type the length of the second Kathetus :  ");// this method asks to give the
																						// number of the second KAT.
		int sum = Kathetus1 * Kathetus1 + Kathetus2 * Kathetus2;// THIS method gives the sum of 1st^2 and 2nd^2 kathets
		double Hyp = Math.sqrt(sum); // this method gives then square root of the given sum above
		println("The Hypotenus is:  " + Hyp); // this method writes the calculated hyp number

	}
}
