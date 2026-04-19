package com.ui.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver(); // Loose Coupling ( we can change frequently to another browser whene ever needed)
		driver.manage().window().maximize();
		driver.get("https://automationpractice.techwithjatin.com/my-account");
		driver.findElement(By.className("login")).click();
		driver.findElement(By.id("email")).sendKeys("senax57658@sixoplus.com");
		driver.findElement(By.id("passwd")).sendKeys("0987654321");
		
		// Another way
		By SignInButtonLoctaor= By.name("SubmitLogin");
		WebElement signInButtonWebElement = driver.findElement(SignInButtonLoctaor);
		signInButtonWebElement.click();
		
		driver.quit();
		
	}

}
