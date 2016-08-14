/**
 * Created by alexandrugutu on 12/11/15.
 */

import java.io.*;
import java.math.*;
import java.util.*;

public class Solution
	{
		public static void main(String[] args)
			{
				try
					{
						BufferedReader inputData = new BufferedReader(new InputStreamReader(System.in));

						int inputNumber = Integer.parseInt(inputData.readLine());

						BigInteger outputNumber = new BigInteger("1");

						for (int i = 2; i <= inputNumber; i++)
							{
								outputNumber = outputNumber.multiply(new BigInteger(String.valueOf(i)));
							}

						System.out.println(outputNumber);

					}
				catch (IOException e)
					{
						System.out.println("Error, no line(s) to read");

					}
			}
	}
