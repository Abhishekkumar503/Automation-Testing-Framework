package com.ui.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.utility.BrowserUtility;

public class LoginTest3 {

	public static void main(String[] args) {
		
		HomePage homepage = new HomePage("chrome");
		String name = homepage.goToLoginPage().login("senax57658@sixoplus.com","9876543210").getUserName();
		System.out.println(name);
		
		homepage.quitDriver();
		}

}
