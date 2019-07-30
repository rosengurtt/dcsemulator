package dcsemulator.ink;

import java.util.ArrayList;
import java.util.List;

public class GetPassengerDetailResponseOutput {

	public GetPassengerDetailResponseOutput(GetPassengerDetailResponse p) {
		if (p.Passengers != null) {
			passengers = new ArrayList<PassengerOutput>();
			for (int i = 0; i < p.Passengers.size(); i++) {
				passengers.add(new PassengerOutput(p.Passengers.get(i)));
			}
		}
	}
	
	
	public List<PassengerOutput> passengers;
}
