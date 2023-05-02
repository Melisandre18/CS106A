
/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;

public class Hangman extends ConsoleProgram {
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private HangmanLexicon wordGuess = new HangmanLexicon();
	private String word = getWord();
	private int guesses = 8;
	private String Dashes = "";
	private String guessWord = getDashes();
	private HangmanCanvas canvas;
	private String incorrectLetter = "";

//creates the second (Graphics) canvas on the screen
	public void init() {
		canvas = new HangmanCanvas();
		add(canvas);
	}

//runs the gameplay and shows the welcome message on the screen
	public void run() {
		/* You fill this in */
		setGame();
		gamePlay();

	}

//it is the code for welcome message when the screen appears, also it connects console canvas  to hangmanlexicon part
	private void setGame() {
		canvas.reset();
		canvas.displayWord(guessWord);
		println("Welcome to Hangman!");
		println("Your word now looks like this " + guessWord);
		println("You have " + guesses + " guesses, GOOD LUCK!.");

	}

//continues playing the game till there are no guesses left, asks o=for another character if already written is falseand checks it.
	private void gamePlay() {
		while (guesses > 0) {

			String letter = readLine("Your guess : ");
			String Lupper = letter.toUpperCase();
			if (correctLetter(letter) == false) {
				letter = readLine("Your guess : ");
				Lupper = letter.toUpperCase();
			}
			checkLetterTrue(Lupper);
			if (checkGamePlay() == true) {
				break;
			}
		}
	}

//checks if there are no more guesses and ends the game, also if the user finds out what the word till there are gusesses left is writes the suitable message
	private boolean checkGamePlay() {
		if (guesses == 0) {
			println("LOOSER ! You're completely hung the word was " + word);
			return true;
		} else if (guessWord.equals(word)) {
			println("You won the game");
			return true;
		}
		return false;
	}

//checks if the goven letter is similar to any letters in the chosen word and changes the suitable dash into this letter on the screen also shows the appearance of the word after change
	private void checkLetterTrue(String Lupper) {
		if (correctLetter(Lupper)) {
			for (int i = 0; i < word.length(); i++) {
				if (Lupper.charAt(0) == word.charAt(i)) {
					if (i > 0) {
						guessWord = guessWord.substring(0, i) + Lupper + guessWord.substring(i + 1);

					}
					if (i == 0) {
						guessWord = Lupper + guessWord.substring(1);

					}
					canvas.displayWord(guessWord);
				}

			}
			checkLetterFalse(Lupper);
			println("The word now looks like this " + guessWord);
		}
	}

//checks is letter is not similar to any of the letter of chosen word and changes the guees number , shows the suitable message on the screen
	private void checkLetterFalse(String Lupper) {
		int amount = 0;
		if (correctLetter(Lupper)) {
			for (int i = 0; i < word.length(); i++) {
				if (Lupper.charAt(0) != word.charAt(i)) {
					amount++;
				}
			}
			if (amount == word.length()) {

				guesses--;
				println("Your letter isn't similar to any in this word");
				println("You have  " + guesses + "  guesses left");
				incorrectLetter = incorrectLetter + Lupper;
				canvas.noteIncorrectGuess(incorrectLetter);
			}
		}
	}

// checks is the user gave the correct letter , if the letter is not letter type, user pressed enter button or so on.
	private boolean correctLetter(String Lupper) {
		if (Lupper.length() > 1) {
			println("Symbol is not correct ");
			return false;
		}
		for (int i = 0; i < Lupper.length(); i++) {
			if (!Character.isLetter(Lupper.charAt(i))) {
				println("Symbol is not correct ");
				return false;
			}
		}
		if (Lupper.isEmpty()) {
			println("Symbol is not correct ");
			return false;
		}
		return true;
	}

//gets the random word from lexicon file
	private String getWord() {
		int wordIndex = rgen.nextInt(0, wordGuess.getWordCount() - 1);
		String correctWord = wordGuess.getWord(wordIndex);
		return correctWord;
	}

//draws as many dashes as tehre are letters in chosen word
	private String getDashes() {
		for (int i = 0; i < word.length(); i++) {
			Dashes += "-";
		}
		return Dashes;
	}

}
