package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.utility.BrowserUtility;

public final class HomePage extends BrowserUtility{
	
	private static final By SIGN_IN_LINK_LOCATOR = By.className("login");
	public HomePage(String browser) {
		super(browser);
		goToWebsite("https://automationpractice.techwithjatin.com/my-account");
		maximizeWindow();
	}


public LoginPage goToLoginPage()
{
	clickOn(SIGN_IN_LINK_LOCATOR);
	LoginPage loginPage = new LoginPage(getDriver());
	return loginPage;
}

}
