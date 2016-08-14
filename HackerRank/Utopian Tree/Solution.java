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
						int height = 1;
						int cycle;

						BufferedReader inputData = new BufferedReader(new InputStreamReader(System.in));

						int numberOfValues = Integer.parseInt(inputData.readLine());
						int[] numberOfCycles = new int[numberOfValues];

						for (int i = 0; i < numberOfValues; i++)
							{
								cycle = Integer.parseInt(inputData.readLine());

								for (int j = 0; j <= cycle; j++)
									{
										if (j % 2 == 0 && j > 0)
											{
												height = height + 1;
											}
										else if (j % 2 == 1 && j > 0)
											{
												height = height * 2;
											}
									}
								System.out.println(height);
								height = 1;
							}


					}
				catch (IOException e)
					{
						System.out.println("Error, no line(s) to read");

					}

			}
	}
