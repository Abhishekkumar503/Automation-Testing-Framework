package com.utility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

public abstract class BrowserUtility {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>(); // storing null at a time

	Logger logger = LoggerUtility.getLogger(this.getClass());

//	Constructor 1
	public BrowserUtility(String browserName) {
		logger.info("Opening " + browserName + " browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			driver.set(new ChromeDriver());
			driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(2000));
		} else if (browserName.equalsIgnoreCase("safari"))
			driver.set(new SafariDriver());
		else {
			logger.error("Invalid Browser..!! Please select chrome of Brave");
		}
	}

	public String takeScreenShot(String name) {
		TakesScreenshot screenShot = (TakesScreenshot) driver.get();

		File screenShotData = screenShot.getScreenshotAs(OutputType.FILE);

//		Date and Time for fileName Uniqueness
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("HH-mm-ss");
		String timeStamp = format.format(date);

//		 Now add timestamp to screenshot name
		String path = System.getProperty("user.dir") + "/screenshot/" + name + "-" + timeStamp + ".png";
		File screenshotFile = new File(path);

		try {
			FileUtils.copyFile(screenShotData, screenshotFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return path;
	}

	public WebDriver getDriver() {
		return driver.get();
	}

	public void setDriver(WebDriver driver) {
		this.driver.set(driver);
	}

//	Constructor 2
	public BrowserUtility(WebDriver driver) {
		super();
		this.driver.set(driver);
		; // initalize the instance variable driver
	}

	public void goToWebsite(String url) {
		logger.info("navigating to the " + url);
		driver.get().get(url);
	}

	public void maximizeWindow() {
		logger.info("Maxmizing the browser");
		driver.get().manage().window().maximize();
	}

	public void clickOn(By locator) {
		WebElement element = driver.get().findElement(locator);
		element.click();
	}

	public void enterText(By locator, String text) {
		WebElement element = driver.get().findElement(locator);
		element.sendKeys(text);
	}

	public String getVisibleText(By locator) {
		WebElement element = driver.get().findElement(locator);
		return element.getText();
	}

	public void quitDriver() {
		logger.info("Quiting the browser!!");
		driver.get().quit();
	}
}
