package com.ui.test;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;

import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LoggerUtility;

public class TestBased {

	protected HomePage homepage;
	Logger logger = LoggerUtility.getLogger(this.getClass());
	
	@BeforeMethod(description = "Load homepage of the webside")
	public void setup()
	{
		homepage = new HomePage("chrome");
		logger.info("Launching Chrome browser!!");
	}
	
	public BrowserUtility getInstance()
	{
		return homepage;
	}
}
