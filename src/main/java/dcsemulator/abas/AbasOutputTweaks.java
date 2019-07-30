package dcsemulator.abas;

public class AbasOutputTweaks {


	
	public static String fixNamespaces(String text) {
		return "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" + 
				"<soap:Body xmlns:ns1=\"http://www.iata.org/IATA/2007/00\">" + 
				text.replaceAll("<", "<ns1:").replaceAll("<ns1:/", "</ns1:")
				.replaceAll("<ns1:[a-zA-Z0-9].:", "<ns1:").replaceAll("<\\/ns1:[a-zA-Z0-9].:", "</ns1:") + 
				"</soap:Body></soap:Envelope>";	
	}
}
