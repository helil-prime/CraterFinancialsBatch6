package step_definitions;

import org.junit.Assert;
import org.openqa.selenium.By;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.Access_control_page;
import pages.Dashboard_page;
import pages.Items_page;
import utils.BrowserUtils;
import utils.Driver;

public class ItemsManagement {
	
	Access_control_page acp = new Access_control_page();
	Dashboard_page dash_page = new Dashboard_page();
	BrowserUtils utils = new BrowserUtils();
	Items_page items_page = new Items_page();
	
	String itemname = "";
	
	// we don't over kill the step definitions with tons of lines of code
	@Given("As a {string} user, I am logged in")
	public void as_a_user_i_am_logged_in(String user_type) {
		acp.login(user_type);
	}
	
	@Given("I navigate to Items tab")
	public void i_navigate_to_items_tab() {
		dash_page.Items_tab.click();
		utils.waitUntilUrlContains("items");
		Assert.assertTrue(items_page.items_items_text.isDisplayed());
	}
	@When("I click on Add Item button")
	public void i_click_on_add_item_button() {
	    items_page.items_add_item_button.click(); 
	}
	@Then("I should be on New Item page")
	public void i_should_be_on_new_item_page() {
		utils.waitUntilUrlContains("items/create");
	    Assert.assertTrue(items_page.items_Input_page_newItem_text.isDisplayed());
	}
	@When("I provide item name {string} price {string} unit {string} and description {string}")
	public void i_provide_item_name_price_unit_and_description(String item_name, String item_price, String item_unit, String item_des) {
		// in order to make the item name unique each time, I am adding random number to the item name before I send keys
		itemname = item_name + utils.randomNumber();
		System.out.println("Random numbered item name is: " + itemname);
		items_page.items_input_page_name_box.sendKeys(itemname);  
	    
		items_page.items_input_page_price_box.sendKeys(item_price);
		
		// how do choose from the unit dropdown?
		items_page.items_input_page_unit_dropdown.click();
		utils.waitForElementToBeVisible(items_page.items_input_page_unit_pc_option);	
		items_page.items_input_page_unit_pc_option.click();
		
		items_page.items_input_page_description.sendKeys(item_des);
	}
	@When("I click on Save Item button")
	public void i_click_on_save_item_button() {
	    items_page.items_page_saveItem_btn.click();
	    utils.waitUntilUrlContains("items");
		Assert.assertTrue(items_page.items_items_text.isDisplayed());
	}
	@Then("The item is added to the item list table")
	public void the_item_is_added_to_the_item_list_table() {
	   items_page.items_page_filter_btn.click();
	   utils.waitForElementToBeVisible(items_page.items_page_filter_name_box);
	   items_page.items_page_filter_name_box.sendKeys(itemname);
	   
	   utils.waitUntilElementVisibleWithLocator(By.xpath("//a[text()='"+itemname+"']"));
	   Assert.assertTrue(Driver.getDriver().findElement(By.xpath("//a[text()='"+itemname+"']")).isDisplayed());
	}
	@Then("I delete the item")
	public void i_delete_the_item() {
	    
	}

}
