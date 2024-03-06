package api_tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Items_management_api {
	
	
	// normally in Java, you would just create a method to do certain things
	// and use the main method to call those methods to execute. 
	
	// in TestNG, you just create a method to do certain things, and you don't need main method to execute.

	
	String token = "4533|mI9RcUpjH0ZtpOHuvM0DaDKQqTsh0VoXKBBg";
	String baseurl = "http://crater.primetech-apps.com";
	
	@Test
	public void list_all_items() {
		Response response = RestAssured
		.given().accept("application/json").auth().oauth2("Bearer " + token)
		.when().get(baseurl+"/api/v1/items");
		
		//response.prettyPrint();
		
		response.then().statusCode(200).contentType("application/json");
	}
	
}
