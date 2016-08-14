/**
 * Created by alexandrugutu on 12/11/15.
 */


import java.io.*;
import java.util.*;

public class Solution
	{
		public static void main(String[] args)
			{
				try
					{
						BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

						int numberOfNegativeValues = 0;
						int numberOfZeroValues = 0;
						int numberOfPositiveValues = 0;

						int numberOfValues = Integer.parseInt(input.readLine());
						String[] stringArrayOfValues = input.readLine().split(" ");
						int[] integerArrayOfValues = new int[numberOfValues];

						for (int i = 0; i < numberOfValues; i++)
							{
								integerArrayOfValues[i] = Integer.parseInt(stringArrayOfValues[i]);
								if (integerArrayOfValues[i] < 0)
									{
										numberOfNegativeValues++;
									}
								else if (integerArrayOfValues[i] == 0)
									{
										numberOfZeroValues++;
									}
								else
									{
										numberOfPositiveValues++;
									}
							}

						System.out.println(String.format("%.6f", (double)numberOfPositiveValues/numberOfValues));
						System.out.println(String.format("%.6f", (double)numberOfNegativeValues/numberOfValues));
						System.out.println(String.format("%.6f", (double)numberOfZeroValues/numberOfValues));

					}
				catch (IOException e)
					{
						System.out.println("Error, no line(s) to read");

					}
			}
	}
