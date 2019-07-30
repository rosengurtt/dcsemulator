package dcsemulator.ink;

public class PassengerOutput {

	public PassengerOutput(Passenger p) {
		passenger_name = p.PassengerName;
		ink_passenger_identifier = p.InkPassengerIdentifier;
		if (p.Itineraries != null) {
			itinerary = new Itinerary2Output[p.Itineraries.length];
			for (int i = 0; i < p.Itineraries.length; i++) {
				itinerary[i] = new Itinerary2Output(p.Itineraries[i]);
			}
			}
		if (p.SSRs != null) {
			ssrs = new ssrOutput[p.SSRs.length];
			for (int i = 0; i < p.SSRs.length; i++) {
				ssrs[i] = new ssrOutput(p.SSRs[i]);
			}
		}
		baggage_tags_destination = p.BaggageTagsDestination;
		if (p.BaggageTags != null) {
		baggage_tags = new BaggageTagOutput[p.BaggageTags.length];
			for (int i = 0; i < p.BaggageTags.length; i++) {
				baggage_tags[i] = new BaggageTagOutput(p.BaggageTags[i]);
			}
		}
		apis_gender = p.ApisGender;
		baggage_allowance_pieces = p.BaggageAllowancePieces;
		baggage_allowance_weight_kg = p.BaggageAllowanceWeightKg;
	}
	
	
	public String passenger_name;
	
    public String ink_passenger_identifier;

    public Itinerary2Output[] itinerary;

    public ssrOutput[] ssrs;

    public String baggage_tags_destination;

    public BaggageTagOutput[] baggage_tags;

    public String apis_gender;

    public String baggage_allowance_pieces;

    public String baggage_allowance_weight_kg;
}
