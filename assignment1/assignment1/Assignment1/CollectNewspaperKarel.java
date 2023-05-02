
/*
 * File: CollectNewspaperKarel.java
 * --------------------------------
 * At present, the CollectNewspaperKarel subclass does nothing.
 * Your job in the assignment is to add the necessary code to
 * instruct Karel to walk to the door of its house, pick up the
 * newspaper (represented by a beeper, of course), and then return
 * to its initial position in the upper left corner of the house.
 */

import stanford.karel.*;

public class CollectNewspaperKarel extends SuperKarel {

	// at the beginning Karel is standing on 3x4 block, facing to the East
	// at the end karel is standing on 3x4 facing to the east with taken beeper from
	// 6x3
	public void run() {
		moveToNewspaper();
		takeTheNewspaper();
		getBack();
		turnRight();
	}

//PRE-Karel is standing on 3x4 ,facing to the EAST, Newspaper is on 6x3.
// Method-This method moves karel from 3x4 to 6x3
//POST-After this method Karel is standing on 6x3.
	private void moveToNewspaper() {
		while (frontIsClear()) {
			move();
		}
		turnRight();
		move();
		turnLeft();
		move();
	}

	// PRE-Newspaper is on 6x3
	// Method- With this method Karel takes the newspaper,puts it in its pocket and
	// says
	// "chemi araa ,chamides"
	// POST-Newspaper is in Karels pocket.
	private void takeTheNewspaper() {
		pickBeeper();
		turnAround();
	}

	// PRE-Karel is standing on 6x3 , facing to the West
	// Method-This method returns Karel on its first Place
	// POST-Karel is standing on 3x4 , Newspaper is in its pocket.
	private void getBack() {
		while (frontIsClear()) {
			move();
		}
		turnRight();

		while (frontIsClear())

		{
			move();
		}
	}
}
