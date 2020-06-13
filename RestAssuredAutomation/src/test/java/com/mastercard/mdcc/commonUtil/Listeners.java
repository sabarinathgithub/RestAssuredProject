package com.mastercard.mdcc.commonUtil;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {
	
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext testContext) {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/RestAssuredAutomationReport.html");
		htmlReporter.config().setDocumentTitle("RestAssured Automation Report");
		htmlReporter.config().setReportName("API Testing Automation");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Project", "Employee API Testing");
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "sabari");
	}
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.PASS, "Test case PASSED is: "+result.getName());
	}
	
	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Testcase Failed is: "+result.getName());
		test.log(Status.FAIL, "Testcase failed is: "+result.getThrowable()); // logs the exception
		
	}
	
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.SKIP, "Testcase Skipped is: "+ result.getName());		
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}
