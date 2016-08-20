/**
 * Created by alexandrugutu on 8/20/16.
 */
public class main
	{
		public static void main(String[] args)
			{
				String input = "vjqmplq muj xlwo tjgudalglo pq nlhw tlcjw ajmmjw ml rullm ml muj mlg le muj gpaj";
				String[] splitInput = input.split(" ");

				char[] charInput = input.toCharArray();

				String modifiedWord = "";
				String[] modifiedInput = new String[input.length()];

				int difference = 60;

				for (int i = 0; i < splitInput.length; i++)
					{
						for (int j = 0; j < splitInput[i].length(); j++)
							{
								int value = (int)splitInput[i].charAt(j) - difference;
								char currentCharacter = (char)value;
								modifiedWord += currentCharacter;
							}

						modifiedInput[i] = modifiedWord;
						modifiedWord = "";

					}

				for (int i = 0; i < splitInput.length; i++)
					{
						System.out.print(modifiedInput[i] + " ");
					}
			}
	}
