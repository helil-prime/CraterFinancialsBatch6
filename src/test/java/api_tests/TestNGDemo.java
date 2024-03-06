package api_tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNGDemo {
	
	
	
	@BeforeMethod
	public void setup() {
		System.out.println("Before method");
	}
	
	@AfterMethod
	public void wrapup() {
		System.out.println("After method");
	}
	
	// each method is considered one test. 
	
  @Test (priority = 1)
  public void demoTest() {
	  
	  // softAssert 
	  // bunch of code
	  // bunch of code
	  
	  System.out.println("Test 1");
  }
  
  
  @Test (priority = 2)
  public void anotherTest() {
	  
	  // HardAssert 
	  // bunch of code
	  // bunch of code
	  
	  System.out.println("Test 2");
  }
  
  
}
