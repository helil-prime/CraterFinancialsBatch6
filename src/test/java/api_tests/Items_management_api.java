package api_tests;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import utils.BrowserUtils;
import utils.DataReader;

public class Items_management_api {
	
	BrowserUtils utils = new BrowserUtils();
	
	
	// normally in Java, you would just create a method to do certain things
	// and use the main method to call those methods to execute. 
	
	// in TestNG, you just create a method to do certain things, and you don't need main method to execute.

	
	String token;
	String baseurl = DataReader.getProperty("app_url");
	int item_id;
	
	Response response;
	
	
	// in RestAssured, we attach the body of the request in the given section.
	
	// injecting a string into a string:  "+variable+"
	
	// login test/function
	@Test (groups= {"smoke-tests"})
	public void login_test() {
		
		String payload = "{\n"
				+ "    \"username\": \""+ DataReader.getProperty("username")+"\",\n"
				+ "    \"password\": \""+ DataReader.getProperty("password")+"\",\n"
				+ "    \"device_name\": \"mobile_app\"\n"
				+ "}";
		
		response = given().contentType("application/json").body(payload)
				.when().post(baseurl+"/api/v1/auth/login");
		
		response.prettyPrint();
		token = response.jsonPath().get("token");
		
	}
	
	
	@Test (dependsOnMethods= {"login_test"})
	public void list_all_items() {
		response = given().accept("application/json").auth().oauth2("Bearer " + token)
		.when().get(baseurl+"/api/v1/items");
		
		//response.prettyPrint();
		
		response.then().statusCode(200).contentType("application/json");
	}
	
	
	
	// create an item and verify the item input is correct in response
	@Test (dependsOnMethods= {"login_test"}, groups= {"smoke-tests", "create_item"})
	public void create_item() {
		// in order to create an item, what do we need?  
		// we need headers, authorization, and body
		
		Map<String, Object> payload = new HashMap<>();
		payload.put("name", "Backpack" + utils.randomNumber());
		payload.put("price", 3800);
		payload.put("unit_id", 11);
		payload.put("description", "nice backpack");
		
		response = given().auth().oauth2("Bearer " + token).body(payload).contentType("application/json")
				.when().post(baseurl + "/api/v1/items");
		
		item_id = response.jsonPath().get("data.id");
		
		String item_name = response.jsonPath().get("data.name");

		System.out.println(" Name of the item created is: " + item_name);
	}
	
	
	@Test (dependsOnMethods="create_item")
	public void get_item() {
		response = given().auth().oauth2("Bearer " + token).accept("application/json")
				.when().get(baseurl + "/api/v1/items/"+item_id);
		
		response.then().statusCode(200).contentType("application/json");
		
		int id = response.jsonPath().get("data.id");
		Assert.assertEquals(id, item_id);
		
	}
	
	
	@Test (dependsOnMethods="get_item")
	public void update_item() {
		File payload = new File("./src/test/resources/testfiles/update_item_data.json");
		
		response = 
				given().auth().oauth2("Bearer " + token).contentType("application/json").body(payload)
				.when().put(baseurl+"/api/v1/items/"+item_id);
		
		response.prettyPrint();
		
		response.then().statusCode(200).contentType("application/json");
	}
	
	
	@Test (dependsOnMethods="update_item")
	public void delete_item() {
		
		String payload = "{\n"
				+ "    \"ids\": [ "+item_id+" ]\n"
				+ "}";
		
		response = given().auth().oauth2("Bearer " + token).contentType("application/json").body(payload)
				.when().post(baseurl + "/api/v1/items/delete");
		
		response.prettyPrint();
		
		response.then().statusCode(200).contentType("application/json");
		boolean deleteSuccess = response.jsonPath().get("success");
		
		Assert.assertTrue(deleteSuccess);
	}
	
}
