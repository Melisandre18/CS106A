import acm.program.ConsoleProgram;

public class blow extends ConsoleProgram {

	public String blowUp(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (Character.isDigit(s.charAt(i))) {
				int amount = Character.getNumericValue(s.charAt(i));
				while (amount > 0) {
					s.replace(s.charAt(i), s.charAt(i + 1));
					amount--;
				}
			}
		}
		return s;
	}
}
