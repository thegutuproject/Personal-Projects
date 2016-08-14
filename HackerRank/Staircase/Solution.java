/**
 * Created by alexandrugutu on 12/11/15.
 */

import java.util.*;
import java.io.*;

public class Solution
	{
		public static void main(String[] args)
			{
				try
					{
						BufferedReader inputData = new BufferedReader(new InputStreamReader(System.in));

						int numberOfSteps = Integer.parseInt(inputData.readLine());

						for (int i = 0; i < numberOfSteps; i++)
							{
								for (int j = 0; j < numberOfSteps; j++)
								{
									if (i + j >= numberOfSteps - 1)
										{
											System.out.print("#");
										}
									else
										{
											System.out.print(" ");
										}
								}
								System.out.println();
							}


					}
				catch (IOException e)
					{
						System.out.println("Error, no line(s) to read");

					}
			}
	}
