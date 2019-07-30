package dcsemulator.ink;

public class ItineraryOutput {

	public ItineraryOutput(Itinerary i) {
		flight_number = i.FlightNumber;
		flight_date = i.FlightDate;
		station_iata = i.StationIata;
		stops_before_bag_destination = i.StopsBeforeBagDestination;
		is_destination = i.IsDestination;
	}
	
    public String flight_number;
	
    public String flight_date;
	
    public String station_iata;
	
    public String stops_before_bag_destination;
    
    public String is_destination;
}
