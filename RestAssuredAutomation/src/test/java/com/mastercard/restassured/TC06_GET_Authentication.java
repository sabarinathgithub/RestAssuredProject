package com.mastercard.restassured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC06_GET_Authentication {
  @Test
  public void f() {
	  RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";
	  
	  PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
	  authScheme.setUserName("ToolsQA");
	  authScheme.setPassword("TestPassword");
	  RestAssured.authentication=authScheme;
	  
	  RequestSpecification httpRequest = RestAssured.given();
	  
	  Response response = httpRequest.request(Method.GET, "/");
	  
	  Assert.assertEquals(response.getStatusCode(), 200);
	  System.out.println(response.getBody().asString());
  }
}
