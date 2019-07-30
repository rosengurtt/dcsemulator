package dcsemulator.halo;

public class GetPassengerDetailsResponse {
	  public enum PassengerDetailsResult
	  {
	    Success,
	    DoesNotExistOrCanceled,
	    InvalidPassengerId,
	    GeneralFailure,
	  }
	  
    public Passenger Passenger;

    public PassengerDetailsResult Result;

    public FlightInfo Flight;

    public BookingInfo Booking;
}
