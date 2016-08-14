/**
 * Created by Alex on 12/10/2015.
 */

import java.io.*;

public class Solution
	{
		public static void main(String[] args)
			{
				try
					{
						BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

						int numberOfValues = Integer.parseInt(input.readLine());
						String[] stringArrayOfValues = input.readLine().split(" ");

						long sumOfArrayValues = 0;


						for (int i = 0; i < numberOfValues; i++)
							{
								sumOfArrayValues += Long.valueOf(stringArrayOfValues[i]);
							}
						System.out.println(sumOfArrayValues);

					}
				catch (IOException e)
					{
						System.out.println("Error, no line(s) to read");

					}
			}

	}
