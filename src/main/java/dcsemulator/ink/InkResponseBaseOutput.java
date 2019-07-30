package dcsemulator.ink;

public class InkResponseBaseOutput {
	
	public InkResponseBaseOutput(String Status, Error[] errors) {
		this.Status = Status;
		this.error = errors;
	}
	
	public String Status;
	
	public Error[] error;
}
