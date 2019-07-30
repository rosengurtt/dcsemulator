package dcsemulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Node;

import dcsemulator.helpers.StringHelpers;
import dcsemulator.helpers.XmlHelpers;

public class SoapParser {

	
public static int getCorrectRow(String mainTag, String xml, ArrayList<HashMap<String,String>> config) {		
		try{
			for (int i = 0; i < config.size(); i++) {
				HashMap<String,String> h = config.get(i);
				String operation =  h.get("requestTypesOperation");				
				String path = h.get("requestTypesXpath");	
				Node matchedNode = XmlHelpers.getNode(xml, path);
				String nodeContent =  (matchedNode!= null)? matchedNode.getTextContent() : "";
				String value = (h.get("requestTypeValueToMatch") != null)? h.get("requestTypeValueToMatch") : "";
				
				if (operation.equals("matchNode") && matchedNode != null) {
					return i;
				}		
				else if (operation.equals("matchValue") && !value.equals("")) {
						if (nodeContent.equals(value)){
							return i;
						}
					}
				else if (operation.equals("contains")) {
					if (nodeContent.contains(value)){
						return i;
					}				
				}
				else if (operation.equals("startsWith")) {
					if (nodeContent.startsWith(value)){
						return i;
					}				
				}
			}
		
		}
		catch (Exception e) {
			int i=5;
		}
		return 0;	
	}
	
	
	public static String getAirline(String xml, HashMap<String,String> config) {
		String path = config.get("airlineXpath");			
		return getNodeContent(xml, path);
	}
	
	public static String getAirport(String xml, HashMap<String,String> config) {
		String path = config.get("airportXpath");			
		return getNodeContent(xml, path);
	}
	
	public static String getPNR(String xml, HashMap<String,String> config) {
		String path = config.get("PNRXpath");			
		return getNodeContent(xml, path);
	}
	
	public static String getUniqueFragment(String xml, HashMap<String,String> config) {
		String regularExpresion = config.get("uniqueFragmentPattern");	
		try {
			Pattern p = Pattern.compile(StringHelpers.removeEnclosingDoubleQuotes(regularExpresion));
			if (p!= null) {
				Matcher m = p.matcher(StringHelpers.removeCarriageReturns(xml));
				if (m.find()) {
					return StringHelpers.removeSpecialChars(m.group(1));
				}
			}
		}
		catch (Exception ex) {
			int i =6;
		}
		
		return "%";		
	}
	
	private static String getNodeContent(String xml, String path) {
		try {
			return XmlHelpers.getNode(xml, path).getTextContent();
		}
		catch(Exception ex) {
			int i=9;
		}
		return "%";
	}
}
