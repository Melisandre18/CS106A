
/*
 * File: Pyramid.java
 * Name: 
 * Section Leader: 
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Pyramid extends GraphicsProgram {

	/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

	/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

	/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;

	public void run() {

		PyramidD();// this method draws the pyramid on the window
	}

	private void PyramidD() {
		int x = getWidth() / 2 - 7 * BRICK_WIDTH; // this method gives the first coordinate of the first brick
		int y = getHeight() - BRICK_HEIGHT; // this method gives the second coordinate of the first brick
		int n = 0;
		for (int i = BRICKS_IN_BASE; i > 0; i--) {
			help(x, y, n); // this method draws a row of a pyramid
			x = x + BRICK_WIDTH / 2;// these coordinates make first row get to the next (higher) place
			y = y - BRICK_HEIGHT;// these coordinates make first row get to the next (higher) place
			n++;
			// this method gets first row to the second with changing the coordinates and
			// making with one less brick
		}
	}

	private GRect rect1(int x, int y) {
		GRect rect = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
		add(rect);
		return rect;
		// this method creates a single rectangle
	}

	private void help(int x, int y, int n) {
		for (int j = BRICKS_IN_BASE - n; j > 0; j--) {
			rect1(x, y);
			x += BRICK_WIDTH;
			// this method draws each row of the pyramid
		}
	}
}
