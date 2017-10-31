package example;

import org.json.JSONObject;

public class TestFunctions {

	public static void main(String[] args) {
		
		System.out.println("generated JSON " + createJSON("Button",1.0,2.0,"buttontext"));
			
	}
	
	
	private static String createJSON(String type, double translateX, double translateY, String text) {
		String jsonString = new JSONObject()
	            .put("TYPE", type)
	            .put("TEXT", text)
	            .put("COORDINATES", new JSONObject()
	                 .put("X", translateX)
	                 .put("Y", translateY)).toString();

		System.out.println(jsonString);
		
		return jsonString;
		
	}

}
