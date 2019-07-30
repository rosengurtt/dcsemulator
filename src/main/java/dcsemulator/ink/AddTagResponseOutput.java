package dcsemulator.ink;

import java.util.ArrayList;
import java.util.List;

public class AddTagResponseOutput extends InkResponseBaseOutput{
	
	public AddTagResponseOutput(AddTagResponse a) {
		super(a.Status, a.errors);
		operation_result = a.OperationResult;
		license_plate =  a.LicensePlate;
		ink_passenger_identifier = a.InkPassengerIdentifier;
		passenger_name = a.PassengerName;
		baggage_tag_status = a.BaggageTagStatus;
		baggage_tag_weight = a.BaggageTagWeight;
		special_baggage_code = a.SpecialBaggageCode;
		if (a.Itineraries != null) {
		itineraries = new ArrayList<ItineraryOutput>();
			for (int i = 0; i < a.Itineraries.size(); i++) {
				ItineraryOutput io = new ItineraryOutput(a.Itineraries.get(i));
				itineraries.add(io);
			}
		}
	}

    public String operation_result;
	
    public String license_plate;
	
    public String ink_passenger_identifier;
	
    public String passenger_name;
	
    public String baggage_tag_status;
	
    public String baggage_tag_weight;
	
    public String special_baggage_code;
	
    public List<ItineraryOutput> itineraries;
    
}
