package dcsemulator.halo;

public class DroppedBag {
	  public enum DroppedBagResult
	  {
	    Dropped,
	    ErrorOverMaxBookingWeight,
	    ErrorOverMaxBagWeight,
	    ErrorUnloadedBag,
	    ErrorNonAllocatedBagTag,
	    ErrorSystemFailure,
	    ErrorDuplicateBagTag,
	  }
    public DroppedBagResult Result;

    public String BagTag;
}
