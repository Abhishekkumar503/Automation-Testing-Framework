package com.ui.pages;

import static com.constant.Env.*;

import org.openqa.selenium.By;

import com.utility.BrowserUtility;
import com.utility.JSONUtility;

public final class HomePage extends BrowserUtility {

	private static final By SIGN_IN_LINK_LOCATOR = By.className("login");

	public HomePage(String browser) {
		super(browser);
//		goToWebsite(readProperty(QA, "url"));  // Here you can change you preference accordingly ( from Properties file )
		goToWebsite(JSONUtility.readUrl(UAT)); // from JSON file
		maximizeWindow();
	}

	public LoginPage goToLoginPage() {
		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginPage = new LoginPage(getDriver());
		return loginPage;
	}
}
