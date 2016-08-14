/**
 * Created by alexandrugutu on 5/8/15.
 */

/**
 * Problem 1

 Write three functions that compute the sum of the numbers in a given list using a for-loop, a while-loop, and recursion.

 Problem 2

 Write a function that combines two lists by alternatingly taking elements. For example: given the two lists [a, b, c] and [1, 2, 3], the function should return [a, 1, b, 2, c, 3].

 Problem 3

 Write a function that computes the list of the first 100 Fibonacci numbers. By definition, the first two numbers in the Fibonacci sequence are 0 and 1, and each subsequent number is the sum of the previous two. As an example, here are the first 10 Fibonnaci numbers: 0, 1, 1, 2, 3, 5, 8, 13, 21, and 34.

 Problem 4

 Write a function that given a list of non negative integers, arranges them such that they form the largest possible number. For example, given [50, 2, 1, 9], the largest formed number is 95021.

 Update: Apparently this problem got a lot of people talking (although not as much as Problem 5 below.) You can click here to read my solution.

 Problem 5

 Write a program that outputs all possibilities to put + or - or nothing between the numbers 1, 2, ..., 9 (in this order) such that the result is always 100. For example: 1 + 2 + 34 – 5 + 67 – 8 + 9 = 100.
 */

public class main
	{
		static int[] numberArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

		public static void main(String[] args)
			{
				sumOfNumbers();
			}

		public static void sumOfNumbers()
			{
				int sumArray = 0;
				int sumWhileLoop = 0;
				int sumRecursion = 0;

				for (int i = 0; i < numberArray.length; i++)
					{
						sumArray += numberArray[i];
					}

				int index = 0;

				while (index < numberArray.length)
					{
						sumWhileLoop += numberArray[index];
						index++;
					}

				sumRecursion = recurisveSum(numberArray.length - 1);

				System.out.println("Summation using Arrays: " + sumArray);
				System.out.println("Summation using While Loop: " + sumWhileLoop);
				System.out.println("Summation using Recursion: " + sumRecursion);
			}

		public static int recurisveSum(int index)
			{
				if (index >= 0)
					{
						return numberArray[index] + recurisveSum(index - 1);
					}
				else
					{
						return 0;
					}
			}

		public void twoListMerge(int[] firstArray, int[] secondArray)
			{
				int firstIndex = firstArray.length;
				int secondIndex = secondArray.length;

				int[] mergedArray = new int[firstIndex + secondIndex];

				if (firstIndex > secondIndex)
					{

					}
			}



	}
