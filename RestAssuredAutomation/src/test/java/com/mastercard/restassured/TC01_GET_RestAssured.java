package com.mastercard.restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC01_GET_RestAssured {
  @Test
  public void getWeatherReport() {
	  //Specify base URI
	  RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
	  
	  //Request object
	  RequestSpecification httpRequest = RestAssured.given();
	  
	  //Response object
	  Response response = httpRequest.request(Method.GET, "/Hyderabad");
	  
	  System.out.println("Response status code is: " + response.getStatusCode());
	  Assert.assertEquals(response.getStatusCode(), 200, "Response code is: ");
	  
	  System.out.println("Response status line is: " + response.getStatusLine());
	  Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK", "Response status line is: ");
	  
	  
	  System.out.println("Response body is: " + response.getBody().asString());
  }
}
