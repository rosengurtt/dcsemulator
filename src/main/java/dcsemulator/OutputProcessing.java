package dcsemulator;

import java.util.ArrayList;
import java.util.HashMap;



import dcsemulator.helpers.XmlHelpers;
import dcsemulator.ink.*;
import dcsemulator.newSkies.NewSkiesOutputTweaks;
import dcsemulator.abas.AbasOutputTweaks;
import dcsemulator.halo.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class OutputProcessing {

		public static String formatSoapOutput(HashMap<String, Object> data) {
			String responseContent = (String) data.get("response");
			ArrayList config = (ArrayList) data.get("requestConfig");
			String airlineCode = (data.get("airline") != null)? (String)data.get("airline"): "";
			String dcsName = (data.get("dcsName") != null)? (String)data.get("dcsName"): "";
			
			try {
				ArrayList<HashMap<String,String>> confi = (ArrayList<HashMap<String,String>>)config;
				
				if (dcsName.toLowerCase().equals("abas")) {
					// We exit the function from here, the call to AbasOutputTweaks.fixNamespaces adds the soap tags
					return AbasOutputTweaks.fixNamespaces(responseContent);
				}
				if (dcsName.toLowerCase().equals("newskies")) {
					responseContent =  NewSkiesOutputTweaks.fixNamespaces(airlineCode, responseContent);
				}
				responseContent = confi.get(0).get("addResponseStart") + responseContent + confi.get(0).get("addResponseEnd");
			}
			catch (Exception ex) {
				int u=9;
			}
			return responseContent;
		}
		

		public static String formatRestOutput(HashMap<String, Object> data) {	
			String responseContent = (String) data.get("response");
			HashMap<String,String> requestInfo = (HashMap<String,String>)data.get("requestInfo");
			HashMap<String, String> inputData = (HashMap<String, String>)data.get("inputData");
			if (responseContent == null) return "";
			String queryPath = inputData.get("queryPath");
			String methodName = requestInfo.get("dcsMethodName");
			String dcsName = (data.get("dcsName") != null)? (String)data.get("dcsName"): "";
			
			if (methodName.toLowerCase().equals("LogOn".toLowerCase())) return responseContent;
			
			String textToRemove=" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"";
			String xmlResponse = responseContent.replace(textToRemove, "");
			Object objectResponse = null;
			try {
				XmlMapper xmlMapper = new XmlMapper();
				if (methodName.toLowerCase().equals("FindPassengersForAirportByBookingRef".toLowerCase())) {
					objectResponse = xmlMapper.readValue(xmlResponse, FindPassengersResponse.class);
				}
				else if (methodName.toLowerCase().equals("AddBagTag".toLowerCase())) {
					objectResponse = xmlMapper.readValue(xmlResponse, AddBagTagResponse.class);
				}
				else if (methodName.toLowerCase().equals("DropPassengerBags".toLowerCase())) {
					objectResponse = xmlMapper.readValue(xmlResponse, DropPassengerBagsResponse.class);
				}
				else if (methodName.toLowerCase().equals("OverrideIcts".toLowerCase())) {
					objectResponse = xmlMapper.readValue(xmlResponse, OverrideIctsResponse.class);
				}
				else if (methodName.toLowerCase().equals("GetPassengerDetails".toLowerCase())) {
					objectResponse = xmlMapper.readValue(xmlResponse, GetPassengerDetailsResponse.class);
				}
				else if (methodName.toLowerCase().equals("GetBagTags".toLowerCase())) {
					objectResponse = xmlMapper.readValue(xmlResponse, GetBagTagsResponse.class);
				}
				else if (methodName.toLowerCase().equals("baggage_tag/add".toLowerCase())) {
					AddTagResponse addTagResponse = xmlMapper.readValue(xmlResponse, AddTagResponse.class);
					objectResponse = new AddTagResponseOutput(addTagResponse);
				}
				else if (methodName.toLowerCase().equals("passenger/search".toLowerCase())) {
					PassengerSearchResponse passengerSearchResponse = xmlMapper.readValue(xmlResponse, PassengerSearchResponse.class);
					objectResponse = new PassengerSearchResponseOutput(passengerSearchResponse);
				}
				else if (methodName.toLowerCase().equals("passenger/get_detail".toLowerCase())) {
					GetPassengerDetailResponse getPassengerDetailResponse = xmlMapper.readValue(xmlResponse, GetPassengerDetailResponse.class);
					objectResponse = new GetPassengerDetailResponseOutput(getPassengerDetailResponse);
				}
				ObjectMapper mapper = new ObjectMapper();
				String jsonResponse = mapper.writeValueAsString(objectResponse);							
	
				return jsonResponse;
			}
			catch (Exception ex) {
				int i=9;
			}			
			return responseContent;
		}
}
