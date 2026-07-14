//package com.ui.listeners;
//
//import java.util.Arrays;
//
//import org.apache.logging.log4j.Logger;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentReporter;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.ui.test.TestBased;
//import com.utility.BrowserUtility;
//import com.utility.ExtendReporterUtility;
//import com.utility.LoggerUtility;
//
//public class TestListener implements ITestListener {
//	Logger logger = LoggerUtility.getLogger(this.getClass());
//
//	ExtentSparkReporter extentSparkReporter;
//	ExtentReports extentReports;
//	private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
//
//	public void onTestStart(ITestResult result) {
//		logger.info(result.getMethod().getMethodName());
//		logger.info(result.getMethod().getDescription());
//		logger.info(Arrays.toString(result.getMethod().getGroups()));
//		ExtendReporterUtility.createExtentTest(result.getMethod().getMethodName());
//	}
//
//	public void onTestSuccess(ITestResult result) {
//		logger.info(result.getMethod().getMethodName() + " " + "PASSED");
//		extentTest.get().log(Status.PASS, result.getMethod().getMethodName() + " " + "PASSED");
//		ExtendReporterUtility.getTest();
//	}
//
//	public void onTestFailure(ITestResult result) {
//		logger.error(result.getMethod().getMethodName() + " " + "FAILED");
//		logger.error(result.getThrowable().getMessage());
//		extentTest.get().log(Status.FAIL, result.getMethod().getMethodName() + " " + "FAILED");
//		extentTest.get().log(Status.FAIL, result.getThrowable().getMessage());
//		
////		Calling the instance
//		Object testClass = result.getInstance();
//		
////		Linking the GetInstance and Listensers
//		BrowserUtility browserUtility = ((TestBased) testClass).getInstance();
////		Classin the screenshot method and the screenshot with store the path
//		String path = browserUtility.takeScreenShot(result.getMethod().getMethodName());
//		
////		Paste the SS to Extend Report
//		ExtendReporterUtility.getTest().addScreenCaptureFromPath(path);
//	}
//
//	public void onTestSkipped(ITestResult result) {
//		logger.warn(result.getMethod().getMethodName() + " " + "SKIPPED");
//		extentTest.get().log(Status.SKIP,result.getThrowable().getMessage());
//	}
//
//	public void onStart(ITestContext context) {
//		logger.info("Test suite Started");
//		ExtendReporterUtility.setupSparkReporter("Extent");
//	}
//
//	public void onFinish(ITestContext context) {
//		logger.info("Test suite Completed");
//		ExtendReporterUtility.flushReport();
//		
//	}
//}


package com.ui.listeners;

import java.io.File;
import java.util.Arrays;

import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.ui.test.TestBased;
import com.utility.BrowserUtility;
import com.utility.ExtendReporterUtility;
import com.utility.LoggerUtility;

public class TestListener implements ITestListener {
    Logger logger = LoggerUtility.getLogger(this.getClass());

    @Override
    public void onTestStart(ITestResult result) {
        logger.info(result.getMethod().getMethodName());
        logger.info(result.getMethod().getDescription());
        logger.info(Arrays.toString(result.getMethod().getGroups()));
        
        // Create test and store in ThreadLocal via Utility
        ExtendReporterUtility.createExtentTest(result.getMethod().getMethodName());
        
        // Log details using the stored test
        ExtentTest test = ExtendReporterUtility.getTest();
        if (test != null) {
            test.log(Status.INFO, result.getMethod().getDescription());
            test.assignCategory(result.getMethod().getGroups());
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info(result.getMethod().getMethodName() + " PASSED");
        ExtentTest test = ExtendReporterUtility.getTest();
        if (test != null) {
            test.log(Status.PASS, result.getMethod().getMethodName() + " PASSED");
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error(result.getMethod().getMethodName() + " FAILED");
        logger.error(result.getThrowable().getMessage());
        
        ExtentTest test = ExtendReporterUtility.getTest();
        
        if (test != null) {
            test.log(Status.FAIL, result.getMethod().getMethodName() + " FAILED");
            test.log(Status.FAIL, result.getThrowable().getMessage());
            
            // Capture Screenshot
            Object testClass = result.getInstance();
            if (testClass instanceof TestBased) {
                BrowserUtility browserUtility = ((TestBased) testClass).getInstance();
                
                if (browserUtility != null && browserUtility.getDriver() != null) {
                    String path = browserUtility.takeScreenShot(result.getMethod().getMethodName());
                    
                    // Fixed: Use MediaEntityBuilder and check if file exists
                    if (path != null && new File(path).exists()) {
                        test.fail("Screenshot attached:", 
                            MediaEntityBuilder.createScreenCaptureFromPath(path).build());
                    } else {
                        test.fail("Screenshot file not found or path is null");
                    }
                }
            }
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn(result.getMethod().getMethodName() + " SKIPPED");
        ExtentTest test = ExtendReporterUtility.getTest();
        if (test != null && result.getThrowable() != null) {
            test.log(Status.SKIP, result.getThrowable().getMessage());
        }
    }

    @Override
    public void onStart(ITestContext context) {
        logger.info("Test suite Started");
        ExtendReporterUtility.setupSparkReporter("Extent");
    }

    @Override
    public void onFinish(ITestContext context) {
        logger.info("Test suite Completed");
        ExtendReporterUtility.flushReport();
    }
    
    // Optional: Implement other methods if needed
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
}   