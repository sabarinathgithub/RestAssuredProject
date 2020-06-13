package com.mastercard.mdcc.employee.testcases;

import org.testng.annotations.Test;

import com.mastercard.mdcc.commonUtil.RestUtils;
import com.mastercard.mdcc.employee.base.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;

public class TC004_PUT_EmployeeRecord extends BaseTest{
	RequestSpecification httpRequest;
	Response response;
	String empName= RestUtils.empName();
	String empSalary = RestUtils.empSalary();
	String empAge = RestUtils.empAge();

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		logger.info("*****************TC004_PUT_EmployeeRecord*************");
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", empName);
		requestParams.put("salary", empSalary);
		requestParams.put("age", empAge);
		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());
		
		response = httpRequest.request(Method.PUT, "/update/"+empId);
		Thread.sleep(2000);
//		System.out.println("response of PUT service call is: "+ response.getBody().asString());
	}

	@Test
	public void modifyEmployeeRecord() {
		System.out.println("Status code of PUT service call is: "+ response.getStatusCode());
	}


}
