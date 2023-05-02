
/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import java.util.ArrayList;

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {

	public static void main(String[] args) {
		new Yahtzee().start(args);
	}

	/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator rgen = new RandomGenerator();
	private int[] diceRolls = new int[N_DICE];
	private int[][] categoryScores;
	private int category;
	private int[][] selectedCategories;
	private boolean check = false;

//starts the gamePlay
	public void run() {

		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		if (nPlayers > MAX_PLAYERS) {
			nPlayers = dialog.readInt("Enter number of players");
		}
		playerNames = new String[nPlayers];
		categoryScores = new int[nPlayers + 1][N_CATEGORIES + 1];
		selectedCategories = new int[nPlayers + 1][N_CATEGORIES + 1];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

//controls the gamePlay 
	private void playGame() {
		for (int i = 0; i < N_SCORING_CATEGORIES; i++) {
			for (int j = 1; j <= nPlayers; j++) {
				Roll(j);
				choosingCategory(j);
			}
		}
		results();
		win();
	}

//controls dice while rolling( Rolls the dice)
	private void Roll(int playerNum) {
		for (int i = 0; i < N_DICE; i++) {
			int dice = rgen.nextInt(1, 6);
			diceRolls[i] = dice;
		}
		display.printMessage(playerNames[playerNum - 1] + "'s turn, click Roll");
		display.waitForPlayerToClickRoll(playerNum);
		display.displayDice(diceRolls);
		for (int i = 0; i < 2; i++) {
			display.printMessage("select the dice to Reroll");
			display.waitForPlayerToSelectDice();
			for (int j = 0; j < N_DICE; j++) {
				if (display.isDieSelected(j) == true) {
					int dice = rgen.nextInt(1, 6);
					diceRolls[j] = dice;
				}
			}

			display.displayDice(diceRolls);
		}
	}

//checks the chosen category
	private void choosingCategory(int playerNum) {
		while (true) {
			display.printMessage("Select Suitable Category");
			category = display.waitForPlayerToSelectCategory();
			if (selectedCategories[playerNum][category] == 0) {
				calculateCategoryScore(playerNum);
				break;
			}
		}
	}

//calculates score for each category
	private void calculateCategoryScore(int playerNum) {
		selectedCategories[playerNum][category] = 1;
		int totalScore;
		if (checkCategory() == true) {
			countScore(playerNum);
			int score = categoryScores[playerNum][category];
			display.updateScorecard(category, playerNum, score);
			totalScores(playerNum);
			totalScore = categoryScores[playerNum][TOTAL];
			display.updateScorecard(TOTAL, playerNum, totalScore);
		} else {
			categoryScores[playerNum][category] = 0;
			display.updateScorecard(category, playerNum, 0);
			totalScores(playerNum);
			totalScore = categoryScores[playerNum][TOTAL];
			display.updateScorecard(TOTAL, playerNum, totalScore);
		}
	}

//after choosing suitable category this method gives a suitable mark on window for each category
	private void countScore(int playerNum) {
		int amount = 0;

		if (category >= ONES && category <= SIXES) {
			for (int i = 0; i < N_DICE; i++) {
				if (diceRolls[i] == category) {
					amount += category;
				}
			}
		} else if (category == THREE_OF_A_KIND || category == FOUR_OF_A_KIND || category == CHANCE) {
			for (int i = 0; i < N_DICE; i++) {
				amount += diceRolls[i];
			}
		} else if (category == FULL_HOUSE) {
			{
				amount = 25;
			}
		} else if (category == SMALL_STRAIGHT) {
			amount = 30;
		} else if (category == LARGE_STRAIGHT) {
			amount = 40;
		} else if (category == YAHTZEE) {
			amount = 50;
		}
		categoryScores[playerNum][category] = amount;
		check = false;
	}

//counts and updates upper lower and total scores on window
	private void totalScores(int playerNumber) {
		int upperScore = 0;
		int lowerScore = 0;
		int totalScore = 0;
		for (int i = ONES; i <= SIXES; i++) {
			upperScore += categoryScores[playerNumber][i];
		}
		for (int i = THREE_OF_A_KIND; i <= CHANCE; i++) {
			lowerScore += categoryScores[playerNumber][i];
		}
		totalScore = upperScore + lowerScore;
		categoryScores[playerNumber][UPPER_SCORE] = upperScore;
		categoryScores[playerNumber][LOWER_SCORE] = lowerScore;
		categoryScores[playerNumber][TOTAL] = totalScore;
	}

//counts and updates scores on window for each player
	private void results() {
		for (int i = 1; i <= nPlayers; i++) {
			display.updateScorecard(UPPER_SCORE, i, categoryScores[i][UPPER_SCORE]);
			display.updateScorecard(LOWER_SCORE, i, categoryScores[i][LOWER_SCORE]);
			if (categoryScores[i][UPPER_SCORE] >= 63) {
				categoryScores[i][UPPER_BONUS] = 35;
			}
			display.updateScorecard(UPPER_BONUS, i, categoryScores[i][UPPER_BONUS]);
			categoryScores[i][TOTAL] = categoryScores[i][TOTAL] + categoryScores[i][UPPER_BONUS];
			display.updateScorecard(TOTAL, i, categoryScores[i][TOTAL]);
		}
	}

//counts the total value for each player after gamePlay and  shows suitable message for winner on window
	private void win() {
		int winningScore = 0;
		int winningNumber = 0;
		for (int i = 1; i <= nPlayers; i++) {
			int amount = categoryScores[i][TOTAL];
			if (amount > winningScore) {
				winningScore = amount;
				winningNumber = i - 1;
			}
		}
		display.printMessage("Congratulations, " + playerNames[winningNumber]
				+ ", you're the winner with a total score of " + winningScore);
	}

//checks if the amount on the dice is similar to any of the categories that game has
	private boolean checkCategory() {
		if (category >= ONES && category <= SIXES || category == CHANCE) {
			check = true;
		} else if (category == THREE_OF_A_KIND || category == FOUR_OF_A_KIND) {
			checkMediumCategory();
		} else if (category == LARGE_STRAIGHT || category == SMALL_STRAIGHT) {
			checkHardCategory();
		} else if (category == YAHTZEE) {
			checkYahtzee();
		} else if (category == FULL_HOUSE) {
			checkFullHouse();
		}

		return check;
	}

//checks the amount of ones on dice after rolls
	private int checkOnes() {
		int amount = 0;
		for (int i = 0; i < diceRolls.length; i++) {
			if (diceRolls[i] == 1) {
				amount++;
			}
		}
		return amount;
	}

//checks amount od twos on dice after rolls
	private int checkTwos() {
		int amount = 0;
		for (int i = 0; i < diceRolls.length; i++) {
			if (diceRolls[i] == 2) {
				amount++;
			}
		}
		return amount;
	}

//checks amount of threes on dice after rolls
	private int checkTrees() {
		int amount = 0;
		for (int i = 0; i < diceRolls.length; i++) {
			if (diceRolls[i] == 3) {
				amount++;
			}
		}
		return amount;
	}

//checks amount of fours on dice after rolls
	private int checkFours() {
		int amount = 0;
		for (int i = 0; i < diceRolls.length; i++) {
			if (diceRolls[i] == 4) {
				amount++;
			}
		}
		return amount;
	}

//checks amount of fives on dice after rolls
	private int checkFives() {
		int amount = 0;
		for (int i = 0; i < diceRolls.length; i++) {
			if (diceRolls[i] == 5) {
				amount++;
			}
		}
		return amount;
	}

//checks amount of sixes on dice after rolls
	private int checkSixes() {
		int amount = 0;
		for (int i = 0; i < diceRolls.length; i++) {
			if (diceRolls[i] == 6) {
				amount++;
			}
		}
		return amount;
	}

//checks if the category is Yahtzee
	private void checkYahtzee() {
		if (checkOnes() == 5) {
			check = true;
		} else if (checkTwos() == 5) {
			check = true;
		} else if (checkTrees() == 5) {
			check = true;
		} else if (checkFours() == 5) {
			check = true;
		} else if (checkFives() == 5) {
			check = true;
		} else if (checkSixes() == 5) {
			check = true;
		}

	}

//checks if the category is fullhouse
	private void checkFullHouse() {
		int[] amount = new int[6];
		int first = 0;
		int second = 0;
		amount[0] = checkOnes();
		amount[1] = checkTwos();
		amount[2] = checkTrees();
		amount[3] = checkFours();
		amount[4] = checkFives();
		amount[5] = checkSixes();
		for (int i = 0; i < amount.length; i++) {
			if (amount[i] == 2) {
				first++;
			}
			if (amount[i] == 3) {
				second++;
			}
		}
		if (first == 1 && second == 1) {
			check = true;
		} else {
			check = false;
		}
	}

//checks if the category is three of a kind or four of a kind
	private void checkMediumCategory() {
		if (category == THREE_OF_A_KIND) {

			if (checkOnes() >= 3 || checkTwos() >= 3 || checkTrees() >= 3 || checkFours() >= 3 || checkFives() >= 3
					|| checkSixes() >= 3)

			{
				check = true;
			}
		}
		if (category == FOUR_OF_A_KIND) {
			if (checkOnes() >= 4 || checkTwos() >= 4 || checkTrees() >= 4 || checkFours() >= 4 || checkFives() >= 4
					|| checkSixes() >= 4) {
				check = true;
			}
		}
	}

//checks if the category is large straight or small straight
	private void checkHardCategory() {
		if (category == LARGE_STRAIGHT) {
			if ((checkOnes() == 1 && checkTwos() == 1 && checkTrees() == 1 && checkFours() == 1 && checkFives() == 1)
					|| (checkTwos() == 1 && checkTrees() == 1 && checkFours() == 1 && checkFives() == 1)
							&& checkSixes() == 1) {
				check = true;
			}
		} else if (category == SMALL_STRAIGHT) {
			if ((checkOnes() >= 1 && checkTwos() >= 1 && checkTrees() >= 1 && checkFours() >= 1)
					|| (checkTwos() >= 1 && checkTrees() >= 1 && checkFours() >= 1 && checkFives() >= 1)
					|| (checkTrees() >= 1 && checkFours() >= 1 && checkFives() >= 1 && checkSixes() >= 1)) {
				check = true;
			}
		}
	}
}
