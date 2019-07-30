package dcsemulator.ink;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddTagResponse extends InkResponseBase {
	
    public String OperationResult;
	
    public String LicensePlate;
	
    public String InkPassengerIdentifier;
	
    public String PassengerName;
	
    public String BaggageTagStatus;
	
    public String BaggageTagWeight;
	
    public String SpecialBaggageCode;
	
    public List<Itinerary> Itineraries;
}
