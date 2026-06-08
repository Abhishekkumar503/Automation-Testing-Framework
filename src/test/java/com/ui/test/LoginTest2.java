package com.ui.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.utility.BrowserUtility;

public class LoginTest2 {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		HomePage homepage = new HomePage(driver);
//		homepage.goToWebsite("https://automationpractice.techwithjatin.com/my-account");
		LoginPage loginPage = homepage.goToLoginPage();
		loginPage.login("senax57658@sixoplus.com","9876543210");
		
		driver.quit();
		}

}
