package dcsemulator.halo;

public class AddBagTagResponse {
	  public enum AddBagTagResult
	  {
	    TagAdded,
	    ErrorSystemFailure,
	    DuplicateTag,
	  }
	
	public AddBagTagResult Result;
}
