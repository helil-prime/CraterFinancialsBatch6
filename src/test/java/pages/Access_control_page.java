package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.Driver;

public class Access_control_page {
	
	public Access_control_page() {
		PageFactory.initElements(Driver.getDriver(), this);
	}
	
	@FindBy (name="email")
	public WebElement login_username;
	
	@FindBy (name="password")
	public WebElement login_password;
	
	@FindBy (xpath="//button[text()='Login']")
	public WebElement login_loginBtn;
	
	@FindBy (linkText = "Forgot Password?")
	public WebElement login_forgot_password_link;
	
	@FindBy (xpath = "//p[text()='Success!']")
	public WebElement login_success_message;
	
	@FindBy (xpath = "//p[text()='Logged in successfully.']")
	public WebElement login_successful_message;
	
}
