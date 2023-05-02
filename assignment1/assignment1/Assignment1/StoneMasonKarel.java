
/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

	// Karel is standing on 1x1 block facing to teh East
	// at the end karel is standing in the end of the board , at the beginning of
	// the last column
	public void run() {
		turnLeft();

		while (frontIsClear()) {
			fillTheMason();
			if (beepersPresent()) {
				getBack();
				getToTheNext();
			} else {
				putBeeper();
				getBack();
				getToTheNext();
			}
			if (frontIsBlocked()) {
				getRid();
			}
		}
	}

	// Pre-Before doing this method Karel is standing under each mason, facing to
	// the North
	// Method-This method helps Karel put beepers towards mason, if no beeper is
	// there, but if there is any beeper
	// in this place, Karel just moves forward.
	// Post-after this method each mason is filled
	private void fillTheMason() {
		while (frontIsClear()) {
			if (noBeepersPresent()) {
				putBeeper();
			} else {
				move();
			}
		}
	}

	// Pre-Before this method Karel is standing on the top of each mason, facing
	// North
	// Method- this method helps Karel get back to the beginning of mason
	// Post-after this method Karel is standing at the beginning of the mason,facing
	// to the East
	private void getBack() {
		turnAround();
		while (frontIsClear()) {
			move();
		}
		turnLeft();
	}

	// Pre-Before this method Karel is standing at the beginning of the mason,
	// facing to the East
	// Method- this method helps Karel get to the beginning of the following mason
	// Post-after this method Karel is standing under the mason (which is the
	// following one of pre-mason)
	private void getToTheNext() {
		if (frontIsClear()) {
			for (int i = 0; i < 4; i++) {
				move();
			}
			turnLeft();
		} else {

		}
	}

	// Pre-before this method Karel works exactrly how is needed except working on
	// 1x1 block, it just puts beeper and gets to the next mason
	// Method-this method helps Karel get rid of 1x1 block mason.
	// Post-After this method if there are more masons, Karel gets to the next one
	// and continues filling them, if there isn't any, it stops working.
	private void getRid() {
		if (frontIsBlocked()) {
			if (beepersPresent()) {

			} else {
				putBeeper();
				turnRight();
				getToTheNext();
			}
		}
	}
}
