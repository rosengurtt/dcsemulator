package dcsemulator;
import java.util.ArrayList;
import java.util.HashMap;

import dcsemulator.helpers.StringHelpers;
import dcsemulator.helpers.XmlHelpers;
import dcsemulator.SoapParser;
import dcsemulator.RestParser;

public class InputProcessing {
	
	public static HashMap<String,String> extractSearchVariablesFromSOAP(String mainTag, HashMap<String,String> inputData, ArrayList requestConfig) {
		// Create hashmap that will be returned as output
		HashMap<String,String> retHashMap = new HashMap<String,String>();
		String originalPayload = inputData.get("payload");
		try{
			ArrayList<HashMap<String,String>> confi = (ArrayList<HashMap<String,String>>)requestConfig;			
			
			
			//Create a version of the XML that has no namespaces and white space
			String cleanedInput = StringHelpers.removeWhiteSpace(XmlHelpers.removeXmlStringNamespaceAndPreamble(originalPayload));			
			
			// Check if it requires special treatment or it is handled normally
			if (SpecialCases.isSpecial(mainTag)) {
				SpecialCases.processSpecial(mainTag, retHashMap);
			}
			else {
				int rowNumber = SoapParser.getCorrectRow(mainTag, cleanedInput, confi);
				// Extract the fields to do the lookup in the database     
				retHashMap.put("RequestType", confi.get(rowNumber).get("requestType"));
				retHashMap.put("PNR", SoapParser.getPNR(cleanedInput, confi.get(rowNumber)));
				retHashMap.put("Airline", SoapParser.getAirline(cleanedInput, confi.get(rowNumber)));
				retHashMap.put("Airport", SoapParser.getAirport(cleanedInput, confi.get(rowNumber)));
				retHashMap.put("UniqueFragment", "%" + SoapParser.getUniqueFragment(originalPayload, confi.get(rowNumber)) + "%");
			}
		}
		catch (Exception ex) {}
		return retHashMap;
	}
	
	public static HashMap<String,String> extractSearchVariablesFromREST(HashMap<String,String> inputData, HashMap<String,String> requestInfo){
		String dcsName = requestInfo.get("dcsName");
		
		if (dcsName.toLowerCase().equals("halo")) return HaloParser.extractSearchVariables(inputData, requestInfo);
		if (dcsName.toLowerCase().equals("ink")) return InkParser.extractSearchVariables(inputData, requestInfo);
		return new HashMap<String,String>();
	}
	
	
	
	public static String extractMainTag(HashMap<String,String> inputData) {
		return XmlHelpers.getRequestMainTag(inputData.get("payload"));
	}
	

	// Determines if the request received is a SOAP or a REST request looking at the http method and body
	// If it is a REST request, it determines the DCS name and the DCS method
	public static HashMap<String,String> getRequestType(HashMap<String,String> inputData) {
		String queryPath = inputData.get("queryPath");
		String payload = inputData.get("payload");
		HashMap<String,String> retHashMap = new HashMap<String,String>();
		
		if (inputData.get("httpMethod") == "GET" || (payload != null && payload.startsWith("{"))) {
			return RestParser.getDcsAndMethodName(queryPath);
		}
		else {
			retHashMap.put("dcsType", "SOAP");		
			return retHashMap;
		}
	}
	

}
