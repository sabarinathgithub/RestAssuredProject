package com.mastercard.mdcc.employee.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.mastercard.mdcc.employee.base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

public class TC005_Delet_EmployeeRecord extends BaseTest{
	@BeforeClass
	public void getAllEmployees() throws InterruptedException {
		logger.info("***********Testcase05_Delete************");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employees");
		Thread.sleep(200);
		
		//Capture first employee id
		String empId = response.path("data[0].id");
		System.out.println("Emp id is: "+empId);		
		response = httpRequest.request(Method.DELETE, "/delete/"+empId);		
	}
	
	@Test
	public void checkResponseCode() {
		logger.info("Checkign Response Status code...");
		System.out.println("Delete service response status code is: "+ response.getStatusCode());
	}
	
	@AfterClass
	public void quit() {
		logger.info("***********End of Testcase005*********");
	}
  
}
