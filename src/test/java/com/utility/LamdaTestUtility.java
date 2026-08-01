package com.utility;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LamdaTestUtility {

	// ✅ Plain hub URL - NO embedded credentials
	public static final String hubURL = "https://hub.lambdatest.com/wd/hub";
	private static ThreadLocal<WebDriver> driverLocal = new ThreadLocal<>();
	
	public static WebDriver setup(String browser, String testCaseName) {

	    String userName = "ak204479";
	    String accessKey = "LT_FFGiKSnSpdJY8NwutDrtN5t3KgJs7Y8oigez3LpTIEp3X3Z"; // Replace with a newly generated key

	    System.out.println("========== LambdaTest Debug ==========");
	    System.out.println("Username      : " + userName);
	    System.out.println("AccessKey Len : " + (accessKey == null ? "null" : accessKey.length()));
	    System.out.println("Hub URL       : " + hubURL);
	    System.out.println("Browser       : " + browser);
	    System.out.println("Test Name     : " + testCaseName);
	    System.out.println("======================================");

	    MutableCapabilities capabilities = new MutableCapabilities();
	    HashMap<String, Object> ltOptions = new HashMap<>();

	    ltOptions.put("user", userName);
	    ltOptions.put("accessKey", accessKey);
	    ltOptions.put("build", "Selenium 4");
	    ltOptions.put("name", testCaseName);
	    ltOptions.put("platformName", "Windows 10");
	    ltOptions.put("seCdp", true);
	    ltOptions.put("selenium_version", "latest");

	    capabilities.setCapability("LT:Options", ltOptions);
	    capabilities.setCapability("browserName", browser);
	    capabilities.setCapability("browserVersion", "latest");

	    System.out.println("Capabilities:");
	    System.out.println(capabilities);

	    try {
	        System.out.println("Creating RemoteWebDriver session...");

	        WebDriver driver = new RemoteWebDriver(new URL(hubURL), capabilities);

	        System.out.println("Session created successfully!");
	        System.out.println("Session ID: " + ((RemoteWebDriver) driver).getSessionId());

	        driverLocal.set(driver);
	        return driver;

	    } catch (Exception e) {

	        System.out.println("Session creation FAILED");
	        System.out.println("Exception Type : " + e.getClass().getName());
	        System.out.println("Message        : " + e.getMessage());

	        e.printStackTrace();

	        throw new RuntimeException("Failed to create LambdaTest session", e);
	    }
	}

	public static void tearDown() {
		if (driverLocal.get() != null) {
			driverLocal.get().quit();
		}
	}
}