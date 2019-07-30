package dcsemulator;

import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dcsemulator.helpers.StringHelpers;


public class InkParser {
	
	
	public static HashMap<String,String> extractSearchVariables(HashMap<String,String> inputData, HashMap<String,String> requestInfo){
		HashMap<String,String> retHashMap = new HashMap<String,String>();
		try {
			String methodName = requestInfo.get("dcsMethodName");
			String queryString = inputData.get("queryString");
			Map<String, String> parameters = splitQuery(queryString);
			

			retHashMap.put("RequestType", getRequestType(methodName));
			retHashMap.put("PNR", getPNR(methodName, parameters));
			retHashMap.put("Airline", getAirline(methodName, parameters));
			retHashMap.put("Airport", getAirport(methodName, parameters));
			retHashMap.put("UniqueFragment", getUniqueFragment(methodName, parameters));

		}
		catch (Exception ex) {			
		}
		return retHashMap;
	}
	
	
	// Downloaded from the web
	private static Map<String, String> splitQuery(String query) {		
	    Map<String, String> query_pairs = new LinkedHashMap<String, String>();
	    try {
	    String[] pairs = query.split("&");
	    for (String pair : pairs) {
	        int idx = pair.indexOf("=");
	        query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
	    }
	    }
	    catch (Exception ex) {}
	    return query_pairs;
	}

	private static String getRequestType(String method) {
		if (method.equals("baggage_tag/add"))  return "AddTag";
		if (method.equals("passenger/search"))  return "PassengerSearch";
		if (method.equals("passenger/get_detail"))  return "GetPassengerDetail";
		return "%";
	}
	
	
	private static String getAirport(String method, Map<String, String> parameters) {
		if (method.toLowerCase().equals("passenger/search") || method.toLowerCase().equals("passenger/get_detail")) {
			String airport = parameters.get("station_iata");
			if (airport != null && !airport.equals(""))
			return airport;
		}
		return "%";
	}
	private static String getAirline(String method, Map<String, String> parameters) {
		return "%";
	}
	private static String getPNR(String method, Map<String, String> parameters) {
		if (method.toLowerCase().equals("passenger/search")) {
			return parameters.get("booking_reference");
		}
		
		return "%";
	}
	
	private static String getUniqueFragment(String method, Map<String, String> parameters) {
		if (method.toLowerCase().equals("baggage_tag/add")  || method.toLowerCase().equals("passenger/get_detail")) {
			String passengerId = parameters.get("ink_passenger_identifier");
			return "%InkPassengerIdentifier" + StringHelpers.removeSpecialChars(passengerId) + "InkPassengerIdentifier%";
		}
		return "%";
	}

}
