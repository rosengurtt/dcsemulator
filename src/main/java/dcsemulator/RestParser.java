package dcsemulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import dcsemulator.helpers.StringHelpers;

public class RestParser {

	private static String[] haloMethods = new String[]{"AddBagTag" , "CalculateBagCharges" , "CheckInPassenger", "DropPassengerBags",
			"FindPassengersForAirportByBookingRef", "FindPassengersForAirportByLastName", "FindPassengersForFlightByBookingRef",
			"FindPassengersForFlightByLastName", "GetBagTags", "GetFlight", "GetFlights", "GetInfrastructureInfo",
			"GetPassengerDetails", "LogOff", "LogOn", "OverrideIcts", "RemoveBag"};
	
	private static String[] inkMethods = new String[] {"baggage_tag/add", "passenger/search", "passenger/get_detail"};

	// Looking at the queryPath determines if it is a request for a Halo or an Ink DCS and which method is being called
	public static HashMap<String, String> getDcsAndMethodName(String queryPath) {
		HashMap<String,String> retHashMap = new HashMap<String,String>();
		for (int i =  0; i < haloMethods.length; i++) {
			if (queryPath.toLowerCase().contains(haloMethods[i].toLowerCase())) {
				retHashMap.put("dcsName", "Halo");	
				retHashMap.put("dcsMethodName", haloMethods[i]);		
			}		
		}
		for (int i =  0; i < inkMethods.length; i++) {
			if (queryPath.toLowerCase().contains(inkMethods[i].toLowerCase())) {
				retHashMap.put("dcsName", "Ink");	
				retHashMap.put("dcsMethodName", inkMethods[i]);		
			}		
		}	
		retHashMap.put("httpMethod", "REST");		
		return retHashMap;
	}
	


}
