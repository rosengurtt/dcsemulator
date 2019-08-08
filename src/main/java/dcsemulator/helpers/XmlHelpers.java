package dcsemulator.helpers;

import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlHelpers {

	
	public static String removeXmlStringNamespaceAndPreamble(String xmlString) {
		xmlString = xmlString.replaceAll("\r", "").replaceAll("\n",""); // Remove carriage returns
		return xmlString.replaceAll("(<\\?[^<]*\\?>)?", ""). /* remove preamble */
		  replaceAll("xmlns.*?(\"|\').*?(\"|\')", "") /* remove xmlns declaration */
		  .replaceAll("(<)(\\w+:)(.*?>)", "$1$3") /* remove opening tag prefix */
		  .replaceAll("(</)(\\w+:)(.*?>)", "$1$3"); /* remove closing tags prefix */
		}
	
	public static Node getNode(String xml, String path) throws Exception {
		xml = StringHelpers.removeEnclosingDoubleQuotes(xml);
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	        Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));        
	        
	        XPath xPath = XPathFactory.newInstance().newXPath();
	
	        Node node = (Node) xPath.compile(path).evaluate(doc, XPathConstants.NODE);
	        return node;
		}
		catch (Exception ex) {
		
		}
		return null;
	}

	public static String getRequestMainTag(String xml) {
		try {
			String cleanedXml = CleanXml(xml);
			Node node = getNode(cleanedXml, "//Envelope/Body/*");
			if (node == null) {
				node = getNode(cleanedXml, "/*");
			}
			if (node != null ) {
				return node.getNodeName();
			}
		}
		catch (Exception ex) {
			int i=9;
			i=6;
		}
		return "%";
	}
	
	public static String getResponseMainTag(String xml) {
		try {
			Pattern p = Pattern.compile("<([a-zA-Z]*[^a-z^A-Z^ ]*)");
			Matcher m = p.matcher(StringHelpers.removeCarriageReturns(xml));
			if (m.find()) {
				return StringHelpers.removeSpecialChars(m.group(1));
			}
		}
		catch (Exception ex) {}
		return "%";
	}
	
	public static String addResponseMaintTag(String xml, String newTag) {
		// If there is a namespace string in the tag, we have to remove it for the closing tag
		String closingTag = (newTag.indexOf(" ")> -1)? newTag.substring(0, newTag.indexOf(" ")) : newTag;
		
		if (newTag.indexOf(" ")> -1) {
			closingTag = newTag.substring(0, newTag.indexOf(" "));
		}
		return "<"+ newTag + ">" + xml + "</" + closingTag + ">";
	}
	
	public static String fixProblemsWithNullValues(String xml) {
		try {
			
				// Remove domainspace "http://www.w3.org/2001/XMLSchema-instance"
				Pattern p = Pattern.compile("(\\S*=\"http:\\/\\/www.w3.org\\/2001\\/XMLSchema-instance\")");
				Matcher m = p.matcher(xml);
				if (m.find()) {
					xml = xml.replaceAll(m.group(1), "");
				}			
				// Add the domainspace wherever there is a nil value
				p = Pattern.compile("( )(\\S*:)(nil=)");
				m = p.matcher(xml);
				if (m.find()) {
					xml = xml.replaceAll(m.group(2), "");
					xml = xml.replaceAll("nil=", "xmlns=\"http://www.w3.org/2001/XMLSchema-instance\" nil=");
				}
				return xml;
		}
		catch (Exception ex) {}
		return xml;
	}

	public static String replaceResponseMaintTag(String xml, String oldTag, String newTag) {
		if (newTag != null) {
			// If there is a namespace string in the tag, we have to remove it for the closing tag
			String closingTag = (newTag.indexOf(" ")> -1)? newTag.substring(0, newTag.indexOf(" ")) : newTag;
			
			// Because Java for whatever reason doesn't process the regular expression I need in the way it should
			// I have to use a hack:
			// I add at the end a string that I know it wil never happen, and then remove it before returning from the function
			String imposibleString = "IhateJavaBecauseItSucks";
			// I add the string
			xml = StringHelpers.removeCarriageReturns(xml).trim() +  imposibleString;
			// Change opening tag
			xml = xml.replaceFirst("<"+ oldTag, "<" + newTag);
			// Change closing tag
			xml = xml.replace("</" + oldTag + ">" + imposibleString, "</" + closingTag + ">");
			// I remove the added string. Thank you java for being a pain in the ass
			return xml.replaceFirst(imposibleString, "");
		}
		return xml;
	}
	
	private static String CleanXml(String xml) {
		return StringHelpers.removeWhiteSpace(removeXmlStringNamespaceAndPreamble(xml));
	}
	

	

}
