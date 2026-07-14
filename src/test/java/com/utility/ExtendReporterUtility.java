package com.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtendReporterUtility {
	private static ExtentReports extentReports;
	private static ThreadLocal<ExtentTest> extendTest = new ThreadLocal<ExtentTest>();
	public static void setupSparkReporter(String reportName)
	{
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Extend_Reports/" + reportName +".html");
		extentReports = new ExtentReports();
		extentReports.attachReporter(extentSparkReporter);
	}

	public static ExtentTest createExtentTest(String testName)
	{
		ExtentTest test = extentReports.createTest(testName);
		extendTest.set(test);
	}
	
	public static ExtentTest getTest()
	{
		return extendTest.get();
	}
	public static void flushReport()
	{
		extentReports.flush();
	}
}
