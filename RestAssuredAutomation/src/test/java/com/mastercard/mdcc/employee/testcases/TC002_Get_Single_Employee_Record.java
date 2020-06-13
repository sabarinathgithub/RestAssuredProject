package com.mastercard.mdcc.employee.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mastercard.mdcc.employee.base.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC002_Get_Single_Employee_Record extends BaseTest {
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void getEmployeeData() throws InterruptedException {
		logger.info("************Started TC002_Get_Single_Employee_Record************** ");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		httpRequest.header("content-type", "application/json");
		logger.info("Request is: "+RestAssured.baseURI+"/employee/"+empId);
		response = httpRequest.request(Method.GET, "/employee/"+empId);
		Thread.sleep(500);
	}
	
	
	@Test
	public void checkResponseCode() {
		logger.info("Checkign Response Status code.... ");
		logger.info("Response Status code: " +response.getStatusCode());
		Assert.assertEquals(200, response.getStatusCode());
	}	
	
//	@Test
//	void checkEmpIdInResposneBody() {
//		String responseBody = response.getBody().asString();
//		logger.info("Employee ID is: "+empId);
//		logger.info(responseBody);
//		Assert.assertEquals(true, responseBody.contains(empId));
//	}
//	
//	@Test
//	void checkResponseTime() {
//		logger.info("***********checkResponseTime*********");
//		long responseTime = response.getTime();
//		if(responseTime>2000) {
//			logger.warn("Response time is more than expected.");
//		}
//		Assert.assertTrue(responseTime<=2000);;
//	}
//	
//	@Test
//	void checkResponseStatusLine() {
//		System.out.println("Response status line is: " + response.getStatusLine());
//		Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK", "Response status line is: ");
//	}
//	
//	@Test
//	void checkContentType() {
//		String contentType = response.header("Content-Type");
//		Assert.assertEquals("Response Content-Type is: ", "text/html; charset=UTF-8", contentType);
//	}
//	
//	@Test
//	void checkContentLength() {
//		String contentType = response.header("Content-Lenght");
//		Assert.assertTrue("Response Content-Lenght is: ", Integer.parseInt(contentType)<1500);
//	}
//	
//	@Test
//	void checkServerType() {
//		String serverType = response.header("Server");
//		Assert.assertEquals("Response Content-Type is: ", "nginx/1.14.1", serverType);
//	}
//	
	
	@AfterClass
	void tearDown() {
		logger.info("************End of TC002_Get_Single_Employee_Record************** ");
	}

}
 
