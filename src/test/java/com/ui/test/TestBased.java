package com.ui.test;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LamdaTestUtility;
import com.utility.LoggerUtility;

public class TestBased {

	protected HomePage homepage;
	Logger logger = LoggerUtility.getLogger(this.getClass());
	private boolean isLambdaTest;

	@Parameters({ "browser", "isLambdaTest", "isHeadless" })
	@BeforeMethod(description = "Load homepage of the webside")
	public void setup(
			@Optional("chrome") String browser,
	        @Optional("true") Boolean isLambdaTest,
	        @Optional("false") Boolean isHeadless, 
	        ITestResult result) {
		this.isLambdaTest = isLambdaTest;
		WebDriver lambdaDriver;
		if (isLambdaTest) {
			lambdaDriver = LamdaTestUtility.setup(browser.toLowerCase(), result.getMethod().getMethodName());
			homepage = new HomePage(lambdaDriver);
		} else {
			homepage = new HomePage(browser.toLowerCase(), isHeadless);
			logger.info("Launching " + browser + " browser!!");
		}
	}

	public BrowserUtility getInstance() {
		return homepage;
	}

	@AfterMethod(description = "Closing the browser.")
	public void tearUp() {
		if (isLambdaTest)
			LamdaTestUtility.tearDown();
		else {
			homepage.quitDriver();
			logger.info("Closing the browser!!");
		}
	}
}
