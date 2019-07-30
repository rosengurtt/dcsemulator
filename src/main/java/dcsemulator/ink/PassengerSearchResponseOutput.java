package dcsemulator.ink;


public class PassengerSearchResponseOutput {
	
	public PassengerSearchResponseOutput(PassengerSearchResponse p) {
		if (p.PassengerResults != null) {
			passenger_results = new PassengerResultOutput[p.PassengerResults.length];
			for (int i = 0; i < p.PassengerResults.length; i++) {
				passenger_results[i] = new PassengerResultOutput(p.PassengerResults[i]);
			}
		}
	}
	
	public PassengerResultOutput[] passenger_results;
}
