package dcsemulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dcsemulator.helpers.StringHelpers;


public class HaloParser {
	
	
	public static HashMap<String,String> extractSearchVariables(HashMap<String,String> inputData, HashMap<String,String> requestInfo){
		HashMap<String,String> retHashMap = new HashMap<String,String>();
		try {
			String queryPath = inputData.get("queryPath");
			String methodName = requestInfo.get("dcsMethodName");
			String payload = inputData.get("payload");
			
			if (methodName.toLowerCase().equals("LogOn".toLowerCase())) {
				retHashMap.put("Response", 
							"{\"ConnectionId\":\"A5146BE0-36A2-4885-BE8B-32AC23F2AF9A\", \"Result\":0, \"LocationCode\":\"STN\"}");
			}
			else {
				String[] parameters = getMethodArguments(queryPath, methodName);
				retHashMap.put("RequestType", methodName);
				retHashMap.put("PNR", getPNR(methodName, parameters));
				retHashMap.put("Airline", getAirline(methodName, parameters));
				retHashMap.put("Airport", getAirport(methodName, parameters));
				retHashMap.put("UniqueFragment", getUniqueFragment(methodName, parameters, payload));
			}
		}
		catch (Exception ex) {			
		}
		return retHashMap;
	}

	// Returns all the uri parameters and the query parameters that come after the function name
	// For example is the full url is 
	// functionName/something/and/another?thing=passed&in=the&stupid=query
	// it will return 
	//["something", "and", "another", "thing", "passed", "in", "the", "stupid", "query"]
	private static String[] getMethodArguments(String query, String methodName){
		if (!methodName.equals("")) {
			// Remove text up to function name
			int start = query.toLowerCase().indexOf(methodName.toLowerCase()) + methodName.length() + 1;
			if (query.length() < start) return new String[0];
			query = query.substring(start, query.length());
			// Get parameters
			Pattern p = Pattern.compile("([a-zA-Z0-9-_%]+)");
			Matcher m = p.matcher(query);
			ArrayList<String> parameters = new ArrayList<String>();
			
			while (m.find()) {
					parameters.add(m.group(1));
			}				
			return parameters.toArray(new String[parameters.size()]);	
		}
		return new String[0];
	}
	
	private static String getAirport(String method, String[] parameters) {
		if (method.toLowerCase().equals("FindPassengersForAirportByBookingRef".toLowerCase())) {
			return parameters[1];
		}
		return "%";
	}
	private static String getAirline(String method, String[] parameters) {
		return "%";
	}
	private static String getPNR(String method, String[] parameters) {
		if (method.toLowerCase().equals("FindPassengersForAirportByBookingRef".toLowerCase())) {
			return parameters[0];
		}
		return "%";
	}
	
	private static String getUniqueFragment(String method, String[] parameters, String payload) {
		JsonNode jsonPayload = null;
		if (payload != null && payload.length() > 0) {
			try {
				ObjectMapper mapper = new ObjectMapper();
				jsonPayload = mapper.readTree(payload);
			}
			catch (Exception ex) {}
		}
		try {
			if (method.toLowerCase().equals("AddBagTag".toLowerCase())) {
					String bagTag =  jsonPayload.get("BagTag").toString().replace("\"", "");
					return "%BagTag" + StringHelpers.removeSpecialChars(bagTag) + "BagTag%";
			}
			if (method.toLowerCase().equals("DropPassengerBags".toLowerCase())) {
				String passengerId =  jsonPayload.get("PassengerId").toString().replace("\"", "");
				return "%PassengerId" + StringHelpers.removeSpecialChars(passengerId) + "PassengerId%";
			}
			if (method.toLowerCase().equals("GetPassengerDetails".toLowerCase())) {
				String passengerId = parameters[0];
				return "%PassengerId" + StringHelpers.removeSpecialChars(passengerId) + "PassengerId%";
			}
			if (method.toLowerCase().equals("OverrideIcts".toLowerCase())) {
				String passengerId = parameters[3];
				return "%PassengerId" + StringHelpers.removeSpecialChars(passengerId) + "PassengerId%";
			}
		}
		catch (Exception ex) {}
		return "%";
	}

}
