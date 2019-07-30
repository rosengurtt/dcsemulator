package dcsemulator.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

public class StringHelpers {

	public static String removeWhiteSpace(String input) {
	    BufferedReader reader = new BufferedReader(new StringReader(input));
	    StringBuffer result = new StringBuffer();
	    try {
	        String line;
	        while ( (line = reader.readLine() ) != null)
	            result.append(line.trim());
	        return result.toString();
	    } catch (IOException e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public static String removeSpecialChars(String input) {
		return input.replaceAll("[^a-zA-Z0-9]", "");
	}
	public static String removeCarriageReturns(String input) {
		return input.replaceAll("\r", "").replaceAll("\n", "");
	}
	
	
	public static String removeEnclosingDoubleQuotes(String text) {
		if (text.substring(0,1).equals("\"")) text = text.substring(1, text.length()-1 );
		if (text.substring(text.length()-1,text.length()).equals("\"")) text = text.substring(0, text.length()-1 );
		return text;
	}
}
