package step_definitions;

import org.junit.Assert;
import org.openqa.selenium.By;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Access_control_page;
import utils.BrowserUtils;
import utils.DataReader;
import utils.Driver;

public class Access_control_steps {
	
	Access_control_page acp = new Access_control_page();
	BrowserUtils utils = new BrowserUtils();
	
	
	@Given("I am on the login page")
	public void i_am_on_the_login_page() {
	   Driver.getDriver().get(DataReader.getProperty("app_url"));
	}
	@Given("login page components exist")
	public void login_page_components_exist() {
	   Assert.assertTrue(acp.login_username.isDisplayed());
	   Assert.assertTrue(acp.login_password.isDisplayed());
	   Assert.assertTrue(acp.login_loginBtn.isDisplayed());
	   Assert.assertTrue(acp.login_forgot_password_link.isDisplayed());
	}
	@When("I enter valid username and valid password")
	public void i_enter_valid_username_and_valid_password() {
		utils.sendkeysWithActionsClass(acp.login_username, DataReader.getProperty("username"));
		utils.sendkeysWithActionsClass(acp.login_password, DataReader.getProperty("password"));
	}
	@When("I click login button")
	public void i_click_login_button() {
	    utils.clickWithActionsClass(acp.login_loginBtn);
	}
	@Then("I should be on the Dashboard page")
	public void i_should_be_on_the_dashboard_page() {
	   utils.waitUntilUrlContains("dashboard");
	   String currentUrl = Driver.getDriver().getCurrentUrl();
	   Assert.assertEquals("http://crater.primetech-apps.com/admin/dashboard", currentUrl);
	   Assert.assertTrue(currentUrl.contains("dashboard"));  
	}
	@Then("the success message displays")
	public void the_success_message_displays() {
	    Assert.assertTrue(acp.login_success_message.isDisplayed());
	}
	
	
	// valid login variable steps 
	@Then("I should be on the {string} page")
	public void i_should_be_on_the_page(String menu_item) {
	    String classValues = Driver.getDriver().findElement(By.xpath("//a[text()='"+menu_item+"']")).getAttribute("class");
	    Assert.assertTrue(classValues.contains("active"));
	}
	@Then("{string} message displays")
	public void message_displays(String successMessage) {
		Assert.assertTrue(Driver.getDriver().findElement(By.xpath("//p[text()='"+successMessage+"']")).isDisplayed());
		Assert.fail();
	}

	
	
	// invalid login tests
	
	String userEmail;
	String userPassword;
	
	@When("I enter username {string} and password {string}")
	public void i_enter_username_and_password(String useremail, String userpassword) {
		userEmail = useremail;
		userPassword = userpassword;
		
	    utils.sendkeysWithActionsClass(acp.login_username, useremail);
	    utils.sendkeysWithActionsClass(acp.login_password, userpassword);
	}
	
	@Then("I should not be logged in")
	public void i_should_not_be_logged_in() {
		
	   if (userEmail == "" || userPassword == "") {
		   utils.waitForElementToBeVisible(acp.login_field_is_required);
		   
		Assert.assertTrue(acp.login_field_is_required.isDisplayed());
	}else {
		utils.waitForElementToBeVisible(acp.login_credential_not_match_message);
		Assert.assertTrue(acp.login_credential_not_match_message.isDisplayed());
	}
	     
	}

}
