
/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {
	private static final int R1 = 72;
	private static final int R2 = 47;
	private static double R3 = 21.5;

	public void run() {
		getTarget();
	}

	private void getTarget() {

		circle1();// this method draws 1st circle
		circle2();// this method draws 2nd circle
		circle3();// this method draws 3rd circle
	}

	private void circle1() {
		// this is radius of the first circle
		GOval Circle1 = new GOval(getWidth() / 2 - R1, getHeight() / 2 - R1, 2 * R1, 2 * R1); // this method draws the
																								// first in the middle
																								// of the window
		Circle1.setFilled(true);
		Circle1.setColor(java.awt.Color.red);// this method colours the first circle
		add(Circle1);// this method shows the 1st circle on the screen
	}

	public void circle2() {
		GOval Circle2 = new GOval(getWidth() / 2 - R2, getHeight() / 2 - R2, 2 * R2, 2 * R2);// this method draws the
																								// second circle
		Circle2.setFilled(true);
		Circle2.setColor(java.awt.Color.WHITE);// this method colours the second circle
		add(Circle2);// this method shows the 2nd circle on the screen
	}

	public void circle3() {
		GOval Circle3 = new GOval(getWidth() / 2 - R3, getHeight() / 2 - R3, 2 * R3, 2 * R3); // this method draws the
																								// third circle
		Circle3.setFilled(true);
		Circle3.setColor(java.awt.Color.red); // this method colours the third circle
		add(Circle3); // this method shows the 3rd circle on the screen
	}
}
