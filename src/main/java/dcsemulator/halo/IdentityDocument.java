package dcsemulator.halo;

public class IdentityDocument {
	  public enum Gender
	  {
	    Unknown,
	    Male,
	    Female,
	  }
	  public enum IdentityDocumentType
	  {
	    Unknown,
	    Passport,
	    IdentityCard,
	    GroupPassport,
	    RefugeeDocument,
	    Visa,
	  }
	  
    public int Id;

    public IdentityDocumentType DocumentType;

    public String FirstName;

    public String LastName;

    public String Number;

    public String Nationality;

    public String ExpiryDate;

    public String IssuingState;

    public String DateOfBirth;

    public IctsStatus IctsStatus;

    public Gender Gender;
}
