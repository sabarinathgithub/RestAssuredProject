package com.mastercard.restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC03_GET_Request {
  @Test
  public void googleMapTest() {
	  RestAssured.baseURI = "https://maps.googleapis.com";
	  
	  RequestSpecification httpRequest = RestAssured.given();
	  
	  Response response = httpRequest.request(Method.GET, "/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");
	  
	  //Response body
	  System.out.println("Response body is: " + response.getBody().asString());
	  
	  //Validate Response status code
	  System.out.println("Response status code is: "+ response.getStatusCode());
	  Assert.assertEquals(response.getStatusCode(), 200);
	  
	  //Validate Headers
	  System.out.println("Response header content-type is: "+ response.header("Content-Type"));
	  Assert.assertEquals(response.header("Content-Type"), "application/xml; charset=UTF-8");
	  
	  System.out.println("Response header Content-Encoding is: " + response.header("Content-Encoding"));
	  Assert.assertEquals(response.header("Content-Encoding"), "gzip");
	  
  }
}
