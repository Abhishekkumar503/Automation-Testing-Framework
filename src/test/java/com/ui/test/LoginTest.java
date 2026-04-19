package com.ui.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.utility.BrowserUtility;

public class LoginTest {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver(); // Loose Coupling ( we can change frequently to another browser whene ever needed)
		BrowserUtility browserUtility = new BrowserUtility(driver);
		browserUtility.goToWebsite("https://automationpractice.techwithjatin.com/my-account");
		browserUtility.maximizeWindow();
		
		browserUtility.clickOn(By.className("login"));
		browserUtility.enterText(By.id("email"),"senax57658@sixoplus.com");
		driver.findElement(By.id("passwd")).sendKeys("0987654321");
		
		// Another way
		By SignInButtonLoctaor= By.name("SubmitLogin");
		WebElement signInButtonWebElement = driver.findElement(SignInButtonLoctaor);
		signInButtonWebElement.click();
		
		driver.quit();
		
	}

}
