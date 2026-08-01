package com.ui.pages;

import static com.constant.Env.*;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;
import com.utility.JSONUtility;
import com.utility.LoggerUtility;

public final class HomePage extends BrowserUtility {

	private static final By SIGN_IN_LINK_LOCATOR = By.className("login");
	Logger logger = LoggerUtility.getLogger(this.getClass());

	public HomePage(String browser, boolean isHeadless) {
		super(browser,isHeadless);
//		goToWebsite(readProperty(QA, "url"));  // Here you can change you preference accordingly ( from Properties file )
		goToWebsite(JSONUtility.readUrl(UAT).getUrl()); // from JSON file
		maximizeWindow();
	}

	public HomePage(WebDriver driver) {
		super(driver);
		goToWebsite(JSONUtility.readUrl(UAT).getUrl()); // from JSON file
		maximizeWindow();
	}
	
	public HomePage(String browser) {
		super(browser);
		goToWebsite(JSONUtility.readUrl(UAT).getUrl()); // from JSON file
		maximizeWindow();
	}

	public LoginPage goToLoginPage() {
		logger.info("Navigating to the Login page!");
		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginPage = new LoginPage(getDriver());
		return loginPage;
	}
}
