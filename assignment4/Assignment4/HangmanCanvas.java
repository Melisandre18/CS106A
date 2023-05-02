
/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

public class HangmanCanvas extends GCanvas {
	/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;

//	Resets the display so that only the scaffold appears 
	public void reset() {

		getScaffold();
	}

	// Updates the word on the screen with the letters that have been guessed while
	// playing

	public void displayWord(String word) {
		double x = getWidth() / 2 - BEAM_LENGTH;
		double y = getHeight() / 2 + SCAFFOLD_HEIGHT / 2 + FOOT_LENGTH;
		GLabel wordToKnow = new GLabel(word, x, y);
		if (getElementAt(x, y) != null) {
			remove(getElementAt(x, y));
		}
		add(wordToKnow);

	}

//	
//	  Updates the display on the screen by adding the incorrect gueesed letter in
//	  the screen under the given word. also it starts drawing the picture step by
//	  step after each incorrect guess.

	public void noteIncorrectGuess(String incorrectLetter) {
		double x = getWidth() / 2 - BEAM_LENGTH;
		double y = getHeight() / 2 + SCAFFOLD_HEIGHT / 2 + LOWER_ARM_LENGTH;
		GLabel incorrectLetters = new GLabel(incorrectLetter, x, y);
		if (getElementAt(x, y) != null) {
			remove(getElementAt(x, y));
		}
		add(incorrectLetters);
		if (incorrectLetter.length() == 1) {
			getHead();
		}
		if (incorrectLetter.length() == 2) {
			getBody();
		}
		if (incorrectLetter.length() == 3) {
			getLeftArm();
		}
		if (incorrectLetter.length() == 4) {
			getRightArm();
		}
		if (incorrectLetter.length() == 5) {
			getLeftLeg();
		}
		if (incorrectLetter.length() == 6) {
			getRightLeg();
		}
		if (incorrectLetter.length() == 7) {
			getLeftFoot();
		}
		if (incorrectLetter.length() == 8) {
			getRightFoot();
		}
	}

//draws the Scaffold
	private void getScaffold() {
		double X = getWidth() / 2 - BEAM_LENGTH;
		double Y = getHeight() / 2 - SCAFFOLD_HEIGHT / 2;
		GLine scaffold = new GLine(X, Y, X, Y + SCAFFOLD_HEIGHT);
		add(scaffold);
		GLine beam = new GLine(X, Y, getWidth() / 2, Y);
		add(beam);
		GLine rope = new GLine(getWidth() / 2, Y, getWidth() / 2, Y + ROPE_LENGTH);
		add(rope);
	}

//draws the head
	private void getHead() {
		double x = getWidth() / 2 - HEAD_RADIUS;
		double y = getHeight() / 2 - SCAFFOLD_HEIGHT / 2 + ROPE_LENGTH;
		GOval Head = new GOval(x, y, 2 * HEAD_RADIUS, 2 * HEAD_RADIUS);
		add(Head);
	}

//draws the body
	private void getBody() {
		double x = getWidth() / 2;
		double y = getHeight() / 2 - SCAFFOLD_HEIGHT / 2 + ROPE_LENGTH + 2 * HEAD_RADIUS;
		GLine Body = new GLine(x, y, x, y + BODY_LENGTH);
		add(Body);
	}

// draws the left arm
	private void getLeftArm() {
		double x = getWidth() / 2;
		double y = getHeight() / 2 - SCAFFOLD_HEIGHT / 2 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		GLine LeftArm = new GLine(x, y, x - UPPER_ARM_LENGTH, y);
		GLine Leftover = new GLine(x - UPPER_ARM_LENGTH, y, x - UPPER_ARM_LENGTH, y + LOWER_ARM_LENGTH);
		add(LeftArm);
		add(Leftover);
	}

//draws the right arm
	private void getRightArm() {
		double x = getWidth() / 2;
		double y = getHeight() / 2 - SCAFFOLD_HEIGHT / 2 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD;
		GLine RightArm = new GLine(x, y, x + UPPER_ARM_LENGTH, y);
		GLine Rightover = new GLine(x + UPPER_ARM_LENGTH, y, x + UPPER_ARM_LENGTH, y + LOWER_ARM_LENGTH);
		add(RightArm);
		add(Rightover);
	}

//draws the right leg
	private void getRightLeg() {
		double x = getWidth() / 2;
		double y = getHeight() / 2 - SCAFFOLD_HEIGHT / 2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH;
		GLine RightLeg = new GLine(x, y, x - HIP_WIDTH / 2, y);
		GLine RightLegOver = new GLine(x - HIP_WIDTH / 2, y, x - HIP_WIDTH / 2, y + LEG_LENGTH);
		add(RightLeg);
		add(RightLegOver);
	}

//draws the right foot
	private void getRightFoot() {
		
		double x = getWidth() / 2;
		double y = getHeight() / 2 - SCAFFOLD_HEIGHT / 2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
		GLine RightFoot = new GLine(x - HIP_WIDTH / 2, y, x - FOOT_LENGTH - HIP_WIDTH / 2, y);
		add(RightFoot);

	}

//draws the left leg
	private void getLeftLeg() {
		double x = getWidth() / 2;
		double y = getHeight() / 2 - SCAFFOLD_HEIGHT / 2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH;
		GLine LeftLeg = new GLine(x, y, x + HIP_WIDTH / 2, y);
		GLine LeftLegOver = new GLine(x + HIP_WIDTH / 2, y, x + HIP_WIDTH / 2, y + LEG_LENGTH);
		add(LeftLeg);
		add(LeftLegOver);

	}

//draws the left foot
	private void getLeftFoot() {
		double x = getWidth() / 2;
		double y = getHeight() / 2 - SCAFFOLD_HEIGHT / 2 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH;
		GLine LeftFoot = new GLine(x + HIP_WIDTH / 2, y, x + HIP_WIDTH / 2 + FOOT_LENGTH, y);
		add(LeftFoot);
	}
}
