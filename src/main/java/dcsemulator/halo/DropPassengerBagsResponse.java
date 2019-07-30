package dcsemulator.halo;

public class DropPassengerBagsResponse {
	  public enum DropPassengerBagsResult
	  {
	    Dropped,
	    ErrorSystemFailure,
	    ErrorInvalidPaymentAmount,
	    ErrorInvalidBagWeight,
	    ErrorFlightNotOpenForCheckIn,
	    ErrorPassengerNotCheckedIn,
	    ErrorBagDropFailure,
	  }
    public DropPassengerBagsResult Result;

    public DroppedBag[] DroppedBags;
}
