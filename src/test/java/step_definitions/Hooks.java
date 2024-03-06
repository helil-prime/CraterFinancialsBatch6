package step_definitions;

import java.time.Duration;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.Driver;

public class Hooks {
	
	
	// before hook runs before every scenario. 
		@Before
		public void setup() {
			Driver.getDriver().manage().window().maximize();
			Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		}
		
		// after hook runs after the last step of each scenario
		@After
		public void wrapup(Scenario scenario) {
			if (scenario.isFailed()) {
				final byte[] screenshot = ((TakesScreenshot) 
						Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "screenshot");
			}
			Driver.quitDriver();
		}

}
