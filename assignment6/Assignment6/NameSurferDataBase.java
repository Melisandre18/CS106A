import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import acm.util.ErrorException;
import acmx.export.java.io.FileReader;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	private Map<String, NameSurferEntry> namesbase = new HashMap<>();
	private NameSurferEntry entry;

	/* Constructor: NameSurferDataBase(filename) */
	/**
	 * Creates a new NameSurferDataBase and initializes it using the data in the
	 * specified file. The constructor throws an error exception if the requested
	 * file does not exist or if an error occurs as the file is being read.
	 */
	public NameSurferDataBase(String filename) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			while (true) {
				String line = reader.readLine();
				if (line == null)
					break;
				entry = new NameSurferEntry(line);
				namesbase.put(entry.getName(), entry);
			}
			reader.close();
		} catch (IOException ex) {
			throw new ErrorException(ex);
		}
	}

	/* Method: findEntry(name) */
	/**
	 * Returns the NameSurferEntry associated with this name, if one exists. If the
	 * name does not appear in the database, this method returns null.
	 */
	public NameSurferEntry findEntry(String name) {
		Character ch = name.charAt(0);
		if (Character.isLowerCase(ch)) {
			ch = Character.toUpperCase(ch);
		}
		String ch1 =ch.toString();
		Character ch2;
		for (int i = 1; i < name.length(); i++) {
			ch2 = name.charAt(i);
			if (Character.isUpperCase(ch2)) {
				ch2 = Character.toLowerCase(ch2);
			}
			ch1 += ch2;
		}
		name=ch1;
		if (namesbase.containsKey(name)) {
			return namesbase.get(name);
		} else {
		}
		return null;
	}

}
