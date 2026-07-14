package com.ui.pages;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;
import com.utility.LoggerUtility;

public final class LoginPage extends BrowserUtility{
	
	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	private static final By EMAIL_TEXT_BOX_LOCATOR = By.id("email");
	private static final By PASSWORD_TEXT_BOX_LOCATOR = By.id("passwd");
	private static final By SUBMIT_BUTTON_LOCATOR = By.name("SubmitLogin");


	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public MyAccountPage login(String email, String password)
	{
		enterText(EMAIL_TEXT_BOX_LOCATOR, email);
		logger.info("Entering the email : " + email);
		enterText(PASSWORD_TEXT_BOX_LOCATOR, password);
		logger.info("Entering the password : " + password);
		clickOn(SUBMIT_BUTTON_LOCATOR);
		logger.info("Click on the Submit button!");
		MyAccountPage myAccountPage = new MyAccountPage(getDriver());
		logger.info("Navigating the Account Page!!");
		return myAccountPage;
	}

}
