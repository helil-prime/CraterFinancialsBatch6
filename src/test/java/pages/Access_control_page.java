package pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.BrowserUtils;
import utils.DataReader;
import utils.Driver;

public class Access_control_page {
	
	BrowserUtils utils = new BrowserUtils();
	
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
	
	@FindBy (xpath = "//p[text()='These credentials do not match our records.']")
	public WebElement login_credential_not_match_message;
	
	@FindBy (xpath = "//span[text()='Field is required']")
	public WebElement login_field_is_required;
	
	
	public void login(String user_type) {
		Driver.getDriver().get(DataReader.getProperty("app_url"));
		
		 Assert.assertTrue(login_username.isDisplayed());
		 Assert.assertTrue(login_password.isDisplayed());
		 Assert.assertTrue(login_loginBtn.isDisplayed());
		 Assert.assertTrue(login_forgot_password_link.isDisplayed());
		 
		 switch (user_type) {
		 case "level1" :
			 utils.sendkeysWithActionsClass(login_username, DataReader.getProperty("level1_username"));
			 utils.sendkeysWithActionsClass(login_password, DataReader.getProperty("level1_password"));
			 utils.clickWithActionsClass(login_loginBtn);
			 utils.waitUntilUrlContains("dashboard");
			 String currentUrl = Driver.getDriver().getCurrentUrl();
			 Assert.assertEquals("http://crater.primetech-apps.com/admin/dashboard", currentUrl);
			 Assert.assertTrue(currentUrl.contains("dashboard"));
			break;
		 case "level2" :
			 utils.sendkeysWithActionsClass(login_username, DataReader.getProperty("level2_username"));
			 utils.sendkeysWithActionsClass(login_password, DataReader.getProperty("level2_password"));
			 utils.clickWithActionsClass(login_loginBtn);
			 utils.waitUntilUrlContains("dashboard");
			 String url = Driver.getDriver().getCurrentUrl();
			 Assert.assertEquals("http://crater.primetech-apps.com/admin/dashboard", url);
			 Assert.assertTrue(url.contains("dashboard"));
			 break;
		 case "level3" :
			 utils.sendkeysWithActionsClass(login_username, DataReader.getProperty("level3_username"));
			 utils.sendkeysWithActionsClass(login_password, DataReader.getProperty("level3_password"));
			 utils.clickWithActionsClass(login_loginBtn);
			 utils.waitUntilUrlContains("dashboard");
			 String Url = Driver.getDriver().getCurrentUrl();
			 Assert.assertEquals("http://crater.primetech-apps.com/admin/dashboard", Url);
			 Assert.assertTrue(Url.contains("dashboard"));
		default:
			utils.sendkeysWithActionsClass(login_username, DataReader.getProperty("username"));
			utils.sendkeysWithActionsClass(login_password, DataReader.getProperty("password"));
			utils.clickWithActionsClass(login_loginBtn);
			 utils.waitUntilUrlContains("dashboard");
			 String dashboardUrl = Driver.getDriver().getCurrentUrl();
			 Assert.assertEquals("http://crater.primetech-apps.com/admin/dashboard", dashboardUrl);
			 Assert.assertTrue(dashboardUrl.contains("dashboard"));
		}
		 
	}
	
}
