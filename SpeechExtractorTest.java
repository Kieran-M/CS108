import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class SpeechExtractorTest
{
	public static boolean isNameLine(String s)
	{
		for (int i = 0; i < s.length(); i++)
			if ((s.charAt(i) > 'a' && s.charAt(i) < 'z') || (s.charAt(i) > '0' && s.charAt(i) < '9') || s.charAt(i) == '=')
				return false;
		return true;
	}
	public void runExtractor(File fileName)
	{
		try
		{
			// stores everything to write into the file
			// - character speech
			// - act/scene number
			// - dividers
			ArrayList<String> out = new ArrayList<String>();
			Scanner in = new Scanner(System.in); // input scanner
			
			System.out.println("Please enter the play's file name with no extensions: ");
			String nameOfFile = in.nextLine(); // filename input
			
			
			// keeps asking for filename until it's a valid filename or until input is !exit!
			while (!fileName.exists() && !nameOfFile.equals("!exit!")) 
			{
				System.out.println("File does not exist! Please try again with a new file name:");
				nameOfFile = in.nextLine();
				fileName = new File("Texts/" + nameOfFile + ".txt");
			}
			
			System.out.println("Play opened!\nWelcome to the Extractor. When you're finished, type !EXIT! to exit.\n");

			in.close();

			// extract the names of all characters into an arraylist
			ArrayList<String> listOfCharNames = new ArrayList<String>();
			Scanner nameScanner = new Scanner(fileName);
			String nameTemp = "Not working proper";
			
			// runs through the file, extracting character names into the list
			while (nameScanner.hasNextLine())
			{
				nameTemp = nameScanner.nextLine();
				if (isNameLine(nameTemp))
					if (!listOfCharNames.contains(nameTemp))
					{
						System.out.println(nameTemp);
						listOfCharNames.add(nameTemp);
					}
			}
			
			nameScanner.close();
			// list iteration counter
			int listCounter = 1;
			
			
			// main functionality - loops until it's done all characters
			while (listCounter < listOfCharNames.size())
			{
				out = new ArrayList<String>();
				Scanner file = new Scanner(fileName);
				String characterName = "INVALID_NAME";
				
				System.out.println("Character to be extracted: " + listOfCharNames.get(listCounter));
				if (!listOfCharNames.get(listCounter).equals(""))
					characterName = listOfCharNames.get(listCounter); // character name in uppercase
				
				listCounter++;
//				if (++listCounter == listOfCharNames.size())
//					keepRunning = false;
				
				// temporary values
				String temp;
				int counter = 0;
				
				while (file.hasNextLine()) // reads the file
				{
					// if the speech belongs to the character, extract it
					if ((temp = file.nextLine()).startsWith(characterName))
					{
						while (!temp.equals("") && file.hasNextLine() && !temp.startsWith("[")) // while the character speaks
						{
							out.add(temp);
							temp = file.nextLine();
							counter++;
						}
					}
					else if (temp.startsWith("ACT ") || temp.startsWith("Scene ")) // extracts act number
					{
						out.add(temp + "\n=====================");
						temp = file.nextLine();
					}
				}
				
				if (counter != 0) // confirms that the character exists and has speech instead of creating an empty file
				{
					// creates a directory for the play
					new File("Extracts/" + nameOfFile).mkdirs();
					
					PrintWriter writer = new PrintWriter("Extracts/" + nameOfFile + "/" + characterName + ".txt", "UTF-8");
					
					// presents the character
					writer.println(characterName + " IN " + nameOfFile.toUpperCase());
					
					// extracts the number of lines given character has in the play
					writer.println("This character has " + counter + " lines within the play.\n");
					
					// clears unused acts and scenes
					for (int i = 0; i < out.size(); i++)
					{
						if (out.get(i).startsWith("Scene "))
						{
							if (i < out.size() - 1 && (out.get(i + 1).startsWith("ACT ") || out.get(i + 1).startsWith("Scene ")))
							{
								out.remove(i);
								i--;
							}
						}
						if (out.get(i).startsWith("ACT "))
						{
							if (i < out.size() - 1 && out.get(i + 1).startsWith("ACT "))
							{
								out.remove(i);
								i--;
							}
						}
					}
					
					// writes the file
					for (int i = 0; i < out.size(); i++)
					{
						if (i != 0 && (out.get(i).startsWith(characterName)
								|| out.get(i).startsWith("ACT ") 
								|| out.get(i).startsWith("Scene ")))
						{
							writer.println();
						}
						writer.println(out.get(i));
						if (i == out.size() - 1)
							System.out.println("Extracted " + counter + " lines of speech!\n");
					}
					writer.close();
				}
				// Does nothing if character has no speech
				else
					System.out.println("Nothing to extract! Invalid character name, or they just don't speak.\n");
				file.close();
			}
		}
		// exception handling
		catch (FileNotFoundException e)
		{
			System.out.println("Could not find file");
		}
		catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
	}
	
	public static void main (String [] args)
	{
		runExtractor();
	}
}

