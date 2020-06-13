package com.mastercard.restassured;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC05_GET_VerifyResponseBody {
  @Test
  public void getWeatherReport() {
	  //Specify base URI
	  RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
	  
	  //Request object
	  RequestSpecification httpRequest = RestAssured.given();
	  
	  //Response object
	  Response response = httpRequest.request(Method.GET, "/Hyderabad");
	  
	  String responseBody = response.getBody().asString();
	  System.out.println(responseBody);	  
	  Assert.assertEquals(responseBody.contains("Hyderabad"), true);
	  
	  JsonPath jsonpath = response.jsonPath();
	  System.out.println(jsonpath.get("City"));
	  System.out.println(jsonpath.get("Temperature"));
	  System.out.println(jsonpath.get("Humidity"));
	  System.out.println(jsonpath.get("WeatherDescription"));
	  System.out.println(jsonpath.get("WindSpeed"));
	  System.out.println(jsonpath.get("WindDirectionDegree"));
	  
	  Assert.assertEquals(jsonpath.get("City"), "Hyderabad");
	  Assert.assertEquals(jsonpath.get("Temperature"), "30.32 Degree celsius");
	  Assert.assertEquals(jsonpath.get("Humidity"), "30 Percent");
  }
}
