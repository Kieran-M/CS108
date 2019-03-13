import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class extractor {

	private String file;
	private ArrayList<String> out;
	
	
	
	public static boolean isNameLine(String s)
	{
		for (int i = 0; i < s.length(); i++)
			if ((s.charAt(i) > 'a' && s.charAt(i) < 'z') || (s.charAt(i) > '0' && s.charAt(i) < '9') || s.charAt(i) == '=')
				return false;
		return true;
	}
	
	public String runExtractor(String filename) {
		try {
		file = filename;
		
		return file;
		}catch(Exception e) {
			return "Cant find file";
		}
	}
}

