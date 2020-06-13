package com.mastercard.mdcc.employee.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mastercard.mdcc.employee.base.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import junit.framework.Assert;

public class TC001_Get_All_Employees extends BaseTest{
	@BeforeClass
	public void getAllEmployees() throws InterruptedException {
		logger.info("***********Started Testcase01************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");
		Thread.sleep(200);
	}
	
	@Test
	public void checkResponseCode() {
		logger.info("Checkign Response Status code...");
		Assert.assertEquals(200, response.getStatusCode());
	}
	
	@Test
	void checkResponseBody() {
		logger.info("***********checkResponseBody*********");
		String responseBody = response.getBody().asString();
		System.out.println(responseBody);
		logger.info(responseBody);
		Assert.assertEquals(true, responseBody!=null);
	}
	
	@Test
	void checkResponseTime() {
		logger.info("***********checkResponseTime*********");
		long responseTime = response.getTime();
		if(responseTime>6000) {
			logger.warn("Response time is more than expected.");
		}
		Assert.assertTrue(responseTime<=6000);;
	}
	
	@Test
	void checkContentEncoding() {
		logger.info("***********checkContentEncoding*********");
		String contentEncoding = response.header("Content-Encoding");
		logger.info("response header Content Encoding is:" + contentEncoding);
		Assert.assertEquals("gzip", contentEncoding);
	}
	
	@Test
	void checkContentLength() {
		logger.info("***********checkContentLength*********");
		String contentLength = response.header("Content-Length");
		logger.info("response header Content Length is:" + contentLength);
		
		if(Integer.parseInt(contentLength)<100) {
			logger.warn("Content Length is more than 100");
		}
		Assert.assertTrue(Integer.parseInt(contentLength)>100);;
	}
	
	@Test
	void checkCookies() {
		String cookie = response.getCookie("PHPSESSID");
		logger.info("cookie is: " + cookie);
		//Assert.assertTrue(cookie!=null);
	}
	
	@AfterClass
	public void quit() {
		logger.info("***********End of Testcase001*********");
	}
  
}
