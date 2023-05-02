import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import acm.util.ErrorException;
import acmx.export.java.io.FileReader;

/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

public class HangmanLexicon {
// makes the array list where the words from HangmanLexicon  will be written 
	private ArrayList<String> Words = new ArrayList<String>();

//creates a file reader and writes all the lines in the array that are given on this file while there are no more files 
	public HangmanLexicon() {

		try {
			BufferedReader rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
			while (true) {
				String line = rd.readLine();
				if (line == null)
					break;
				Words.add(line);
			}
			rd.close();
		} catch (Exception ex) {
			throw new ErrorException(ex);
		}
	}

	// Returns the word at the specified index
	public String getWord(int index) {
		return Words.get(index);

	}

	// Returns the number of words in the lexicon
	public int getWordCount() {
		return Words.size();
	}
}
