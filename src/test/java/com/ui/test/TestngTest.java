package com.ui.test;

import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ui.pages.HomePage;
import com.ui.pojo.User;
import com.utility.LoggerUtility;

@Listeners(com.ui.listeners.TestListener.class)
public class TestngTest {
	private HomePage homepage;
	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	@BeforeMethod(description = "Load homepage of the webside")
	public void setup()
	{
		homepage = new HomePage("chrome");
		logger.info("Launching Chrome browser!!");
	}
	
	/*
	 * Test Method
	 * Test Script small
	 * you cannot have conditional statement. loop, try catch in you test method
	 * TestScript -> Test steps
	 * Reduce the use of local variable
	 * Atleast one assertion.
	 */

	@Test(description = "Verify with valid user login into application" , groups = {"e2e","sanity"}, dataProviderClass = com.ui.loginDataProvider.LoginDataProvider.class, dataProvider = "loginDataProvider", retryAnalyzer = com.ui.listeners.MyRetryAnalyzer.class)
	public void loginTest(User user){
		logger.info("Starting my Login test with JSON");
		String name = homepage.goToLoginPage().login(user.getEmail(),user.getPassword()).getUserName();
		logger.info("Login JSON test completed!!");
		Assert.assertEquals(name, "Abis K");
		}
	
	@Test(description = "Verify with valid user login into application" , groups = {"e2e","sanity"}, dataProviderClass = com.ui.loginDataProvider.LoginDataProvider.class, dataProvider = "csvDataProvider")
	public void loginCSVTest(User user){
		logger.info("Starting my Login test with CSV");
		String name = homepage.goToLoginPage().login(user.getEmail(),user.getPassword()).getUserName();
		Assert.assertEquals(name, "Abis K");
		logger.info("Login CSV test completed!!");
		}
	
//	@Test(description = "Verify with valid user login into application" , groups = {"e2e","sanity"}, dataProviderClass = com.ui.loginDataProvider.LoginDataProvider.class, dataProvider = "excelDataProvider")
//	public void loginExcelTest(User user){
//		logger.info("Starting my Login test with excel");
//		String name = homepage.goToLoginPage().login(user.getEmail(),user.getPassword()).getUserName();
//		Assert.assertEquals(name, "Abis K");
//		logger.info("Login excel test completed!!");
//		}
	
	@AfterMethod(description = "Closing the browser.")
	public void tearUp()
	{
		  homepage.quitDriver();
		  logger.info("Closing the browser!!");
	}
}
