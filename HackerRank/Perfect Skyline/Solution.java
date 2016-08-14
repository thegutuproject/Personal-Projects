/**
 * Created by alexandrugutu on 12/11/15.
 */

import java.util.*;
import java.io.*;

public class Solution
	{
		public static void main(String[] args)
			{

				boolean perfectSkyline = false;

				try
					{
						BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

						int numberOfValues = Integer.parseInt(input.readLine());

						if (numberOfValues % 2 == 1)
							{
								perfectSkyline = true;
							}
						else
							{
								perfectSkyline = false;
							}

						String[] stringArrayOfValues = input.readLine().split(" ");
						int[] integerArrayOfValues = new int[numberOfValues];

						for (int i = 0; i < numberOfValues; i++)
							{
								integerArrayOfValues[i] = Integer.parseInt(stringArrayOfValues[i]);
							}

						for (int i = 0; i < Math.floor(numberOfValues/2); i++)
							{
								if (integerArrayOfValues[i+1] <= integerArrayOfValues[i])
									{
										perfectSkyline = false;
										break;
									}
							}

						for (int i = 0; i < Math.floor(numberOfValues/2); i++)
							{
								if (integerArrayOfValues[i] != integerArrayOfValues[numberOfValues - i - 1])
									{
										perfectSkyline = false;
										break;
									}
							}

					if (perfectSkyline)
						{
							System.out.println("Perfect");
						}
					else
						{
							System.out.println("Not perfect");
						}


					}
				catch (IOException e)
					{
						System.out.println("Error, no line(s) to read");

					}
			}
	}
