package com.mastercard.restassured;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC02_POST_Request {
  @Test
  public void getWeatherReport() throws InterruptedException {
	  //Specify base URI
	  RestAssured.baseURI = "http://restapi.demoqa.com/customer";
	  
	  //Request object
	  RequestSpecification httpRequest = RestAssured.given();
	  
	  //Request body object
	  JSONObject requestParams = new JSONObject();
	  requestParams.put("FirstName", RandomStringUtils.random(10, true, false));
	  requestParams.put("LastName", RandomStringUtils.random(6, true, false));
	  requestParams.put("UserName", RandomStringUtils.random(8, true, false));
	  requestParams.put("Password", RandomStringUtils.random(6, true, true));
	  requestParams.put("Email", RandomStringUtils.random(8)+"@gmail.com");
	  
	  httpRequest.body(requestParams.toJSONString());
	  
	  httpRequest.header("content-type", "application/json");
	  
	  //Response
	  Response response = httpRequest.request(Method.POST, "/register");
	  Thread.sleep(250);
	  
	  System.out.println("Response code is: "+ response.getStatusCode());
	  Assert.assertEquals(response.getStatusCode(), 201, "Response code is: ");
	  
	  System.out.println("Response body is: " + response.getBody().asString());
	  
	  String responseContent = response.jsonPath().getString("SuccessCode");
	  System.out.println("SuccessCode in the response is: " + responseContent);
	  Assert.assertEquals(responseContent, "OPERATION_SUCCESS", "Response content is: ");
  }
}
