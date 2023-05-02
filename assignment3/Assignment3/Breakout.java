
/*
 * File: Breakout.java
 * -------------------
 * Name:Tamar Gagnidze
 * Section Leader: #13 - Tato Katsitadze
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH = (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	private static final int NTURNS = 3;

	private int x = BRICK_SEP / 4;
	private int y = BRICK_Y_OFFSET;
	private GRect rect;
	private GOval crcl;
	private GRect pad;
	private double vx, vy;
	private RandomGenerator speed = RandomGenerator.getInstance();
	private static final int PAUSE = 15;
	private int BRICKN;
	private int num = NTURNS;

	/* Method: run() */
	/** Runs the Breakout program. */
	public void run() {
		pattern();
		play();
	}

//Method makes the ball move
	private void play() {

		ballMove();
	}

// Method draws all objects on the window
	private void pattern() {
		getRects();
		getPad();
		getBall();
	}

//Method draws all rectangles on the window
	private void getRects() {
		for (int i = 0; i < NBRICK_ROWS; i++) {
			help(x, y, i);
			x = BRICK_SEP / 4;
			y += BRICK_HEIGHT + BRICK_SEP;
		}
	}

//Method colours all  rectangles
	private void rect1(int x, int y, int i) {
		rect = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
		add(rect);
		rect.setFilled(true);
		if (i <= 1) {
			rect.setColor(Color.RED);
		} else if (i >= 2 && i <= 3) {
			rect.setColor(Color.ORANGE);
		} else if (i >= 3 && i <= 5) {
			rect.setColor(Color.YELLOW);
		} else if (i >= 5 && i <= 7) {
			rect.setColor(Color.GREEN);
		} else if (i >= 7 && i <= 9) {
			rect.setColor(Color.CYAN);
		}
	}

//method makes the ball move and it gives random speed for all sides
	private void ballSpeed() {
		vx = speed.nextDouble(1.0, 3.0);
		if (speed.nextBoolean(0.5)) {
			vx = -vx;
		}
		vy = 3.0;
		if (speed.nextBoolean(0.5)) {
			vy = -vy;
		}
	}

//method checks the objects that are on the same coordinates as the ball is , also it checks if there are any lives left and removes a single brick if ball touches it 
	private void ballMove() {
		ballSpeed();
		BRICKN = 100;
		while (true) {

			crcl.move(vx, vy);
			GObject OBJ = getCollidingObject();
			if (OBJ == pad) {
				vy = -vy;
				if ((crcl.getY() + 2 * BALL_RADIUS >= HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT
						&& crcl.getY() + 2 * BALL_RADIUS < HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT)
						&& (crcl.getX() > pad.getX() && vx < 0) && crcl.getY() <= pad.getY() + PADDLE_HEIGHT) {
					vy = -vy;
				}
			} else if (OBJ != null) {
				remove(OBJ);
				BRICKN--;
				vy = -vy;
				if (BRICKN == 0) {
					removeAll();
					win();
					break;
				}
			}
			other();

			pause(PAUSE);
		}
	}

	// checks if the coordinates of the ball matches coordinates of walls and
	// changes its direction
	private void other() {
		if (crcl.getX() < 0 || crcl.getX() + 2 * BALL_RADIUS >= WIDTH) {
			vx = -vx;
		} else if (crcl.getY() <= 0 && vy < 0) {
			vy = -vy;
		} else if (crcl.getY() + 2 * BALL_RADIUS >= HEIGHT - PADDLE_Y_OFFSET) {

			if (num > 1) {
				num--;
				remove(crcl);
				crcl.setLocation((WIDTH / 2) - (BALL_RADIUS / 2), (HEIGHT / 2) - (BALL_RADIUS / 2));
				add(crcl);
				pause(1000);
			} else {
				removeAll();
				over();
			}
		}
	}

//checks if the sides of the ball and if it touches  any object
	private GObject getCollidingObject() {
		if (getElementAt(crcl.getX(), crcl.getY()) != null) {
			return getElementAt(crcl.getX(), crcl.getY());
		} else if (getElementAt(crcl.getX() + 2 * BALL_RADIUS, crcl.getY()) != null) {
			return getElementAt(crcl.getX() + 2 * BALL_RADIUS, crcl.getY());
		} else if (getElementAt(crcl.getX(), crcl.getY() + crcl.getWidth()) != null) {
			return getElementAt(crcl.getX(), crcl.getY() + crcl.getWidth());
		} else if (getElementAt(crcl.getX() + 2 * BALL_RADIUS, crcl.getY() + 2 * BALL_RADIUS) != null) {
			return getElementAt(crcl.getX() + 2 * BALL_RADIUS, crcl.getY() + 2 * BALL_RADIUS);
		} else {
			return null;
		}
	}

//draws the lines of the rectangles
	private void help(int x, int y, int i) {
		for (int j = 0; j < NBRICKS_PER_ROW; j++) {
			rect1(x, y, i);
			x += BRICK_WIDTH + BRICK_SEP;
		}
	}

//draws the ball 
	private void getBall() {
		crcl = new GOval(WIDTH / 2 - BALL_RADIUS, HEIGHT / 2 - BALL_RADIUS, 2 * BALL_RADIUS, 2 * BALL_RADIUS);
		add(crcl);
		crcl.setFilled(true);
		crcl.setColor(Color.black);

	}

//draws the pad
	private void getPad() {
		pad = new GRect(WIDTH / 2 - PADDLE_WIDTH / 2, getHeight() - PADDLE_HEIGHT - PADDLE_Y_OFFSET, PADDLE_WIDTH,
				PADDLE_HEIGHT);
		add(pad);
		pad.setFilled(true);
		pad.setColor(Color.black);
		addMouseListeners();

	}

//makes the pad move as mouse moves on the window
	public void mouseMoved(MouseEvent e) {

		if (e.getX() <= WIDTH - PADDLE_WIDTH / 2 && e.getX() >= PADDLE_WIDTH / 2) {
			pad.setLocation(e.getX() - PADDLE_WIDTH / 2, pad.getY());
		}
	}

//creates over text after the game is over
	private void over() {
		GLabel over = new GLabel("GAME OVER");
		over.setLocation(WIDTH / 2 - over.getWidth() / 2, HEIGHT / 2);
		add(over);
	}

//creates win text after game is won
	private void win() {
		GLabel win = new GLabel("YOU WON");
		win.setLocation(WIDTH / 2 - win.getWidth() / 2, HEIGHT / 2);
		add(win);
	}

}
