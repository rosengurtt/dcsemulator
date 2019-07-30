package dcsemulator.halo;

public class PassengerInfo {	
	enum PersonType{
	    Adult,
	    Child,
	    Object,
	}
    public String FirstName;
    public String LastName;
    public String BookingReference;
    public String FlightNumber;
    public int Id;
    public PersonType PersonType;
}

