/**
 * Created by alexandrugutu on 9/23/15.
 */

//		Background
//		A k semi-palindrome is defined as a string with exactly k pairs of letters that are not palindromic.
//		Thus, the semi-palindromic number k quantifies how far any given string is from being a palindrome.
//
//		For example, abbccddeeccbba is 2 semi-palindromic since the "dd" does not coincide with the "ee."
//		Likewise, bbbccddddccbba is 1 semi-palindromic since the first letter, "b" is not palindromic with the last letter "a."
//
//		According to this definition, a string comprised of n characters can be at most n/2 semi-palindromic.
//
//		Coding Exercise:
//
//		1. Write a program in C++ or Java that tests whether any given string is a palindrome.
//
//		2. Write a program in C++ or Java that inputs a string and outputs the semi-palindromic number.


import java.io.*;
import java.util.*;
import java.util.function.BooleanSupplier;

public class Main
	{
		public static void main(String[] args)
			{

				String userText;

				Scanner userInput = new Scanner(System.in);

				System.out.println("Please enter the string of characters you want to check if it is a palindrome: ");

				userText = userInput.nextLine();

				System.out.println();

				if (isPalindrome(userText))
					{
						System.out.println(userText + " is a palindrome.");
					}
				else
					{
						System.out.println(userText + " is not a palindrome.");
						isKPalindrome(userText);
					}

			}

		public static Boolean isPalindrome(String userText)
			{
				userText = userText.toUpperCase();

				int beginning = 0;
				int ending = userText.length() - 1;
				int counter = 0;

				while (beginning < ending)
					{
						if (userText.charAt(beginning) != userText.charAt(ending))
							{
								return false;
							}
						beginning++;
						ending--;
					}
				return true;
			}

		public static void isKPalindrome(String userText)
			{
				userText = userText.toUpperCase();

				int beginning = 0;
				int ending = userText.length() - 1;
				int counter = 0;

				while (beginning < ending)
					{
						if (userText.charAt(beginning) != userText.charAt(ending))
							{
								counter++;
							}
						beginning++;
						ending--;
					}

				System.out.println("The string " + userText + " is a " + counter + " palindrome");
			}
	}
