
/*
 * File: MidpointFindingKarel.java
 * -------------------------------
 * When you finish writing it, the MidpointFindingKarel class should
 * leave a beeper on the corner closest to the center of 1st Street
 * (or either of the two central corners if 1st Street has an even
 * number of corners).  Karel can put down additional beepers as it
 * looks for the midpoint, but must pick them up again before it
 * stops.  The world may be of any size, but you are allowed to
 * assume that it is at least as tall as it is wide.
 */

import stanford.karel.*;

public class MidpointFindingKarel extends SuperKarel {
	// Karel is standing on 1x1, looking on the East
	// At the end karel is standing in the middle of the first row , facing to the
	// West with beeper on its block
	public void run() {
		if (frontIsClear()) {
			fill();
			done();

		} else {
			putBeeper();
		}
	}

	// Pre-before this method Karel is standing on 1x1 facing East
	// Method-this method moves Karel to the next block and puts beeper until it
	// gets to the end of the first "street" (row)
	// Post-after this method, first row is filled with beepers on each block except
	// the first one (1x1)
	private void fill() {
		while (frontIsClear()) {
			move();
			putBeeper();
		}
	}

	// Pre-before this method karel is standing at the end of the row (with beepers
	// on it)
	// Method-this method helps karel return to the opposite end of the row (that is
	// filled with beepers)
	// Post-after this method karel is standing on the oppostie end of the row (that
	// is filled with beepers)
	private void getBack() {
		turnAround();
		move();
		while (beepersPresent()) {
			move();
		}

	}

	// Pre-before this method Karel is standing at the end of the row (on the block
	// after the block with the last beeper)
	// Method-this method helps karel to turn around, move to the block which is the
	// end of row (with beepers)
	// Post-after this method Karel is standing at one of the ends of the row(with
	// beepers), but with one beeper less( because Karel took it)
	private void pick() {
		turnAround();
		move();
		pickBeeper();
	}

	// Pre-before this method karel is standing at the end of the first row. this
	// row is filled with beeperes (Except firs (1x1) block)
	// Method-this method makes karel move from one end to another and while doing
	// this, it picks one beeper from each side.
	// Post-after this method karel is standing in the middle of the
	private void done()

	{
		while (beepersPresent()) {
			if (!frontIsClear())
				pickBeeper();
			getBack();
			pick();
			move();
			turnAround();
		}
		move();
		putBeeper();
	}
}
