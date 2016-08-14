/**
 * Created by Alex on 12/11/2015.
 */

import java.io.*;

public class Solution
	{
		public static void main(String[] args)
			{
				int hours;
				int minutes;
				int seconds;

				String stringHours;
				String stringMinutes;
				String stringSeconds;

				try
					{
						BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

						String timeString = input.readLine();

						hours = Integer.parseInt(timeString.substring(0, 2));
						minutes = Integer.parseInt(timeString.substring(3, 5));
						seconds = Integer.parseInt(timeString.substring(6, 8));


						if (timeString.substring(8, 10).equals("PM") && hours != 12)
							{
								hours += 12;
							}
						else if (timeString.substring(8, 10).equals("AM") && hours == 12)
							{
								hours = 0;
							}

						if (hours < 10)
							{
								stringHours = '0' + Integer.toString(hours);
							}
						else
							{
								stringHours = Integer.toString(hours);
							}

						if (minutes < 10)
							{
								stringMinutes = '0' + Integer.toString(minutes);
							}
						else
							{
								stringMinutes = Integer.toString(minutes);
							}

						if (seconds < 10)
							{
								stringSeconds = '0' + Integer.toString(seconds);
							}
						else
							{
								stringSeconds = Integer.toString(seconds);
							}

						System.out.println(stringHours + ":" + stringMinutes + ":" + stringSeconds);
					}
				catch (IOException e)
					{
						System.out.println("Error, no line(s) to read");

					}
			}
	}
