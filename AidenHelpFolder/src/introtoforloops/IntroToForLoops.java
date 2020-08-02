package introtoforloops;

/**
 * @(#)IntroToForLoops.java
 *
 *
 * @author 
 * @version 1.00 2014/7/24
 */

import javax.swing.JOptionPane;

public class IntroToForLoops {
	public static void main(String[] args) {
		int number = GetNumber();
		printOddIntegerAndSum(number); // Solution to (i)
		factorial(number); // Solution to (ii)
		divisibleByTwoOrThree(number); // Solution to part (iii)
		printReverse(number); // Solution to part (iv)
		primeNumbers(number); // Solution to part (v)
	}

	// Gets a number from the user
	static int GetNumber() {
		String Str = JOptionPane.showInputDialog("Enter a positive integer value ");
		return Integer.parseInt(Str);
	}

	// This is the solution to part (i)
	static void printOddIntegerAndSum(int n) {
		System.out.println("The number you entered is " + n);
		System.out.println("The odd integers up to " + n + " are -->");
		int sum = 0;
		for (int i = 1; i < n; i = i + 2) {
			System.out.println(i);
			sum = sum + i;
		}
		System.out.println("The sum of the odd integers up to " + n + " is " + sum);

		System.out.println(); // Leaves space between the outputs
		System.out.println();
	}

	// This is the solution to part (ii)
	static void factorial(int n) {
		int fact = 1;
		for (int i = 1; i < n; i += 1) {
			fact = fact * i;
		}
		System.out.println(n + " factorial is " + fact);

		System.out.println(); // Leaves space between the outputs
		System.out.println();
	}

	// This is the solution to part (iii)
	static void divisibleByTwoOrThree(int n) {

		System.out.println("Numbers divisible by 2 or 3 but not both, are -->");

		for (int i = 1; i < n; i += 1) {
			if ((i % 2 == 0 && i % 3 != 0) || (i % 3 == 0 && i % 2 != 0)) {
				System.out.println(i);
			}
		}

		System.out.println(); // Leaves space between the outputs
		System.out.println();
	}

	// This is a solution to part (iv)
	static void printReverse(int n) {

		for (int i = n; i >= 1; i -= 1) {
			for (int k = i; k > 0; k -= 1) {
				System.out.print(i + " ");
			}
			System.out.println();
		}

		System.out.println(); // Leaves space between the outputs
		System.out.println();

	}

	// This is a solution to part (v)
	static void primeNumbers(int n) {
		System.out.println("All the prime numbers up to " + n + " are -->");
		for (int i = 2; i < n; i += 1) {
			if (isPrime(i) == true) {
				System.out.println(i);
			}
		}
	}

	static boolean isPrime(int i) {
		boolean prime = true;
		for (int count = 2; count < i; count += 1) {
			if (i % count == 0) {
				prime = false;
			}
		}
		return prime; 
	}

}
