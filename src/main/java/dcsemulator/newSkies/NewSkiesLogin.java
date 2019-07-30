package dcsemulator.newSkies;

import java.util.HashMap;

public class NewSkiesLogin {

	public static boolean isLogin(String mainTag) {
		if (mainTag == "LogonRequest") return true;
		return false;
	}
	
	public static void processLogin(String mainTag, HashMap<String,String> retHashMap) {
		if (mainTag== "LogonRequest") {
			retHashMap.put("Response", 
					"<s:Envelope xmlns:s=\"http://schemas.xmlsoap.org/soap/envelope/\">" +
					"  <s:Body>" + 
					"    <LogonResponse xmlns=\"http://schemas.navitaire.com/WebServices\">\r\n" + 
					"         <Signature>FdOxePj+Bzw=|OxMtzq3pmRaap1bdIFAoGIpVihGF66pDu3Hts49P2EPTrAvJHrnYdwTAwwxP3x3s5TldCH1ThWfbiy20Rpb75HGfHEquIsLCRz9tgtBORce8YG9gA8Q3wBA5BiMJL1Lu+vqj1bSN6zQ=</Signature>\r\n" + 
					"    </LogonResponse>" +
					"  </s:Body>\r\n" + 
					"</s:Envelope>");
		}
	}
}
