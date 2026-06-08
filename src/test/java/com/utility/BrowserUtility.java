package com.utility;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

public abstract class BrowserUtility {
	
	private WebDriver driver; // storing null at a time

	
	
	public BrowserUtility(String browserName) {
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		}
		else if(browserName.equalsIgnoreCase("safari"))
			driver = new SafariDriver();
		else
			System.out.println("Invalid Browser..!! Please select chrome of Brave");
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public BrowserUtility(WebDriver driver) {
		super();
		this.driver = driver; // initalize the instance variable driver
	}
	
	public void goToWebsite(String url)
	{
		driver.get(url);
	}
	
	public void maximizeWindow()
	{
		driver.manage().window().maximize();
	}

	public void clickOn(By locator)
	{
		WebElement element = driver.findElement(locator);
		element.click();
	}
	
	public void enterText(By locator, String text)
	{
		WebElement element = driver.findElement(locator);
		element.sendKeys(text);
	}
	
	public String getVisibleText(By locator)
	{
		WebElement element = driver.findElement(locator);
		return element.getText();
	}
	
	public void quitDriver()
	{
		driver.quit();
	}
}
