package com.ui.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ui.pages.HomePage;
import com.ui.pojo.User;

public class TestngTest {
	private HomePage homepage;
	
	@BeforeMethod(description = "Load homepage of the webside")
	public void setup()
	{
		homepage = new HomePage("chrome");
	}
	
	/*
	 * Test Method
	 * Test Script small
	 * you cannot have conditional statement. loop, try catch in you test method
	 * TestScript -> Test steps
	 * Reduce the use of local variable
	 * Atleast one assertion.
	 */

	@Test(description = "Verify with valid user login into application" , groups = {"e2e","sanity"}, dataProviderClass = com.ui.loginDataProvider.LoginDataProvider.class, dataProvider = "loginDataProvider")
	public void loginTest(User user){
		String name = homepage.goToLoginPage().login(user.getEmail(),user.getPassword()).getUserName();
		Assert.assertEquals(name, "Abis K");
		homepage.quitDriver();
		}
	
	@Test(description = "Verify with valid user login into application" , groups = {"e2e","sanity"}, dataProviderClass = com.ui.loginDataProvider.LoginDataProvider.class, dataProvider = "csvDataProvider")
	public void loginCSVTest(User user){
		String name = homepage.goToLoginPage().login(user.getEmail(),user.getPassword()).getUserName();
		Assert.assertEquals(name, "Abis K");
		homepage.quitDriver();
		}
	
	@Test(description = "Verify with valid user login into application" , groups = {"e2e","sanity"}, dataProviderClass = com.ui.loginDataProvider.LoginDataProvider.class, dataProvider = "excelDataProvider")
	public void loginExcelTest(User user){
		String name = homepage.goToLoginPage().login(user.getEmail(),user.getPassword()).getUserName();
		Assert.assertEquals(name, "Abis K");
		homepage.quitDriver();
		}
}
