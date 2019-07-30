package dcsemulator.ink;

public class Itinerary2Output {

	public Itinerary2Output(Itinerary2 i) {
		flight_number = i.FlightNumber;
		scheduled_departure_date = i.ScheduledDepartureDate;
		scheduled_departure_time = i.ScheduledDepartureTime;
		station_iata = i.StationIata;
		sector_identifier = i.SectorIdentifier;
		flight_status = i.FlightStatus;
		booking_reference = i.BookingReference;
		ticket_number = i.TicketNumber;
		passenger_status = i.PassengerStatus;
		seat_number = i.SeatNumber;
		sequence = i.Sequence;
	}
	
	
	 public String flight_number;

     public String scheduled_departure_date;

     public String scheduled_departure_time;

     public String station_iata;

     public String sector_identifier;

     public String flight_status;

     public String booking_reference;

     public String ticket_number;

     public String passenger_status;

     public String seat_number;

     public String sequence;
}
