package dcsemulator.ink;

public class BaggageTagOutput {
	
	public BaggageTagOutput(BaggageTag b) {
		license_plate = b.LicensePlate;
		baggage_tag_status = b.BaggageTagStatus;
		baggage_tag_status = b.TagWeightKg;
	}
	
	 public String license_plate;

    public String baggage_tag_status;
    
    public String tag_weight_kg;
}
