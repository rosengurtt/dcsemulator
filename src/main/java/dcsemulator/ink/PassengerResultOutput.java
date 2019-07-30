package dcsemulator.ink;

public class PassengerResultOutput {
	
	public PassengerResultOutput(PassengerResult p) {
		passenger_name = p.PassengerName;
		ink_passenger_identifier = p.InkPassengerIdentifier;
		flight_number = p.FlightNumber;
		scheduled_departure_date = p.ScheduledDepartureDate;
		booking_reference = p.BookingReference;
		passenger_status = p.PassengerStatus;
		sequence = p.Sequance;
		itinerary_summary = p.ItinerarySummary;
	}
    public String passenger_name;
    public String ink_passenger_identifier;
    public String flight_number;
    public String scheduled_departure_date;
    public String booking_reference;
    public String passenger_status;
    public String sequence;
    public String itinerary_summary;
}
