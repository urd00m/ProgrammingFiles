package introtowhileloop;

/**
 * @(#)IntroToWHILELoops.java
 *
 *
 * @author
 * @version 1.00
 */

// This lab uses while loops to solve simple algorithmic problems

public class IntroToWHILELoops {
	public static void main(String[] args) {

		// Thiis section tests part a) of the lab
		String myString = GetAndStoreInput();
		System.out.println(myString);

		// This section tests part b)of the lab
		System.out.println("Enter a character to search for --> ");
		String searchChar = System.console().readLine();
		int position = SecondCharPosition(myString, searchChar);
		System.out.println("The second position of " + searchChar + " is " + position);

	}

	// This method keeps getting input from the user until a "X" or "x" is entered
	// It then stores the input in a string.
	public static String GetAndStoreInput() {
		System.out.println("Enter X or x to exit...");
		String store = "";
		String input = System.console().readLine();
		while (input.equals("X") == false && input.equals("x") == false) {
			store += input;
			System.out.println("Enter X or x to exit...");
			input = System.console().readLine();
		}
		return store;
	}

	// Given a string, this method returns the position of the second occurrence of
	// a given character.
	// If the character occurs less than 2 times it returns -1.
	public static int SecondCharPosition(String str, String Charr) {
		boolean first = false;
		boolean second = false;
		int counter = 0;
		while (counter < str.length()
				&& (first == false || str.substring(counter, counter + 1).equals(Charr) == false)) {
			if (str.substring(counter, counter + 1).equals(Charr) == true)
				first = true;
			counter += 1;
			if (counter < str.length() && (first == true && str.substring(counter, counter + 1).equals(Charr) == true))
				second = true;
		}
		if (second == true) {
			return counter;
		} else {
			return -1;
		}
	}

}