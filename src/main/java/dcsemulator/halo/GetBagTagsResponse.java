package dcsemulator.halo;

public class GetBagTagsResponse {
	  public enum GetBagTagsResult
	  {
	    Success,
	    InvalidNumberOfTags,
	    ErrorSystemFailure,
	  }
    public GetBagTagsResult Result;

    public String[] BagTags;
}
