
/*
 * File: ProgramHierarchy.java
 * Name: 
 * Section Leader: 
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class ProgramHierarchy extends GraphicsProgram {
	private static final int D_LENGTH = 40;
	private static final int D_WIDTH = 150;
	private static final int SPACE = D_WIDTH / 10;

	public void run() {
		getHierarchy(); // this method gives the whole hierarchy diagram
	}

	private void Diagrams() {
		int width = getWidth() / 2; // this method gives the half number of the Width
		int height = getHeight() / 2; // this method gives the half number of the Height
		GRect rect0 = new GRect(width - D_WIDTH / 2, height - 1.5 * D_LENGTH, D_WIDTH, D_LENGTH);
		add(rect0); // this method draws and shows the above rectangle on the window
		GRect rect1 = new GRect(width - 1.5 * D_WIDTH - SPACE, height + D_LENGTH / 2, D_WIDTH, D_LENGTH);
		add(rect1);// this method draws and shows the first rectangle on the window
		GRect rect2 = new GRect(width - D_WIDTH / 2, height + D_LENGTH / 2, D_WIDTH, D_LENGTH);
		add(rect2);// this method draws and shows the second rectangle on the window
		GRect rect3 = new GRect(width + SPACE + 0.5 * D_WIDTH, height + D_LENGTH / 2, D_WIDTH, D_LENGTH);
		add(rect3);// this method draws and shows the third rectangle on the window

	}

	private void Texts() {
		int width = getWidth() / 2;
		int height = getHeight() / 2;
		GLabel text0 = new GLabel("Program", 50, 20);
		text0.setLocation(width - text0.getWidth() / 2, height - D_LENGTH / 2 - text0.getAscent());
		add(text0);// this method creates and shows the texts(in the above rectangle) on its place
		GLabel text1 = new GLabel("GraphicsProgram", 50, 20);
		text1.setLocation(width - SPACE - D_WIDTH - text1.getWidth() / 2, height + 1.5 * D_LENGTH - text1.getAscent());
		add(text1);// this method creates and shows the texts in the first rectangle on its place
		GLabel text2 = new GLabel("ConsoleProgram", 50, 20);
		text2.setLocation(width - text1.getWidth() / 2, height + 1.5 * D_LENGTH - text1.getAscent());
		add(text2);// this method creates and shows the texts in the second rectangle on its place
		GLabel text3 = new GLabel("DialogProgram", 50, 20);
		text3.setLocation(width + SPACE + D_WIDTH - text3.getWidth() / 2, height + 1.5 * D_LENGTH - text3.getAscent());
		add(text3);// this method creates and shows the texts in the third rectangle in its place

	}

	private void Lines() {
		int width = getWidth() / 2;
		int height = getHeight() / 2;
		GLine line1 = new GLine(width, height - D_LENGTH / 2, width - D_WIDTH - SPACE, height + D_LENGTH / 2);
		add(line1);// this method draws the middle line( between above and (under) 2nd)
		GLine line2 = new GLine(width, height - D_LENGTH / 2, width, height + D_LENGTH / 2);
		add(line2);// this method draws the second line (between - above and 1st (under)
					// rectangles)
		GLine line3 = new GLine(width, height - D_LENGTH / 2, width + SPACE + D_WIDTH, height + D_LENGTH / 2);
		add(line3);// this method draws the third line (between above and 3rd (under) rectangles

	}

	private void getHierarchy() {
		Diagrams();
		Texts();
		Lines();
	}
}
