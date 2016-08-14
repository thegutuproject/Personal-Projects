/**
 * Created by Alex on 12/11/2015.
 */

import java.io.*;
import java.util.*;

public class Solution
	{
		public static void main(String[] args)
			{
				BufferedReader inputData = new BufferedReader(new InputStreamReader(System.in));

				int sum = 0;

				try
					{
						int numberOfValues = Integer.parseInt(inputData.readLine());
						String[] arrayOfValues = inputData.readLine().split(" ");

						for (int i = 0; i < numberOfValues; i++)
							{
								sum += Integer.parseInt(arrayOfValues[i]);
							}
					}
				catch (IOException e)
					{
						System.out.println("Error, no line(s) to read");

					}

				System.out.println(sum);
			}
	}
