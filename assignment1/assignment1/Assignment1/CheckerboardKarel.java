
/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
//at the begining Karel is standing on the first block 1x1 facing to the East
	// at the end Karel is standing at the end of the first row , facing to the east
	// , board is filled like checkerboard
	public void run() {
		turnLeft();
		getTheResult();
	}

//with this method karel fills the column like checkerboard
	private void fillRow() {
		putBeeper();
		while (frontIsClear()) {
			move();
			if (frontIsClear()) {
				move();
				putBeeper();
			}
		}
	}

//with this method karel gets to the next column after finishing filling the first one with beepers
	private void getToTheNext() {
		if (facingNorth()) {
			turnRight();
			if (frontIsClear()) {
				if (beepersPresent()) {
					move();
					turnRight();
					move();
				} else {
					move();
					turnRight();
				}
			}

		} else {
			turnLeft();
			if (frontIsClear()) {
				if (beepersPresent()) {
					move();
					turnLeft();
					move();
				} else {
					move();
					turnLeft();
				}
			}
		}
	}

//this method hellps karel on 1x1 board find out if there are any columns or rows and then puts one beeper
	private void other() {
		turnRight();
		fillRow();
	}

//this method helps karel to fill the board fully like a chackerboard
	private void getTheResult() {
		if (frontIsClear()) {
			while (frontIsClear()) {
				fillRow();
				getToTheNext();
			}
		} else {
			other();
		}
	}
}
