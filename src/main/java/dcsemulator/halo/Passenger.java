package dcsemulator.halo;

public class Passenger extends Person {
	  public enum PersonType
	  {
	    Adult,
	    Child,
	    Object,
	  }
    public String BoardingGroup;

    public PersonType PersonType;

    public Infant Infant;

    public Bag[] DroppedBags;

    public String SequenceNumber;

    public String SeatNumber;

    public boolean IsPaidForSeat;
    
    
}
