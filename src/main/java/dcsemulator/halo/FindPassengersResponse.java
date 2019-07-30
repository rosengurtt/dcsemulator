package dcsemulator.halo;

public class FindPassengersResponse {
	  public enum FindPassengersResult
	  {
	    Success,
	    InvalidFlightId,
	    BookingRefOrLastnameMustBeProvided,
	    GeneralFailure,
	  }
	public PassengerInfo[] Passengers;
    public FindPassengersResult Result;
}
