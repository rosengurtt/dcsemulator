package dcsemulator.halo;

public class Bag {
	  public enum BagType
	  {
	    StandardBag,
	    SmallSportingGoods,
	    LargeSportingGoods,
	  }
	  public enum LimitedReleaseCode
	  {
	    None,
	    DamagedBag,
	    InappropriatelyPackedBag,
	    LateCheckIn,
	    UnsuitableForCabinBaggage,
	    PushchairBuggy,
	    FragileInadequatePackaging,
	    UnsuitableForCarriage,
	  }
	  
    public BagType Type;

    public java.math.BigDecimal WeightInKilos;

    public String Tag;

    public LimitedReleaseCode LimitedReleaseCode;
}
