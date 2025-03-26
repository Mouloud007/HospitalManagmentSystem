package com.example.hospitalsustemfx;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EasyScanner
{

	// This class is used to read input from the user.
	// t has methods to read
	// integers, doubles, strings, characters, and lines of text.



	public static String next() {
		String s = sc.next();
		return s;
	}
	//
	private static Scanner sc = new Scanner(System.in);


	public static int nextInt() {
		while (true) {
			try {
				return sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Invalid input. Please enter an integer value.");
				sc.next(); // discard the invalid input
			}
		}
	}

	public static double nextDouble()
	{
		double d = sc.nextDouble();
		return d;
	}

	public static String nextString() {
		while (true) {
			String s = sc.next();

			 if (!s.matches("[a-zA-Z]+")) {
				System.out.println("Invalid input. Please enter only alphabetic characters.");
			} else {
				return s;
			}
		}
	}

	public static String nextLine() {
		return sc.nextLine();
	}
	public static char nextChar() {
		while (true) {
			String input = sc.next();
			if (input.length() == 1) {
				return input.charAt(0);
			} else {
				System.out.println("Invalid input. Please enter a single character.");
			}
		}
	}
}
