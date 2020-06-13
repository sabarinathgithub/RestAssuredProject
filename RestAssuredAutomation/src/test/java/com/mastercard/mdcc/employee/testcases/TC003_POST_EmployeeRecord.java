package com.mastercard.mdcc.employee.testcases;

import org.testng.annotations.Test;
import com.mastercard.mdcc.commonUtil.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.testng.annotations.BeforeClass;

public class TC003_POST_EmployeeRecord {
	RequestSpecification httpRequest;
	Response response;
	String empName = RestUtils.empName();
	String empSalary = RestUtils.empSalary();
	String empAge = RestUtils.empAge();
	
	 @BeforeClass
	 public void setUp() throws InterruptedException {
		 RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		 httpRequest = RestAssured.given();
		 JSONObject requestParams = new JSONObject();
		 requestParams.put("name", empName);
		 requestParams.put("salary", empSalary);
		 requestParams.put("age", empAge);
		 
		 httpRequest.header("Content-Type", "application/json");
		 httpRequest.body(requestParams.toJSONString());
		 
		 response = httpRequest.request(Method.POST, "/create"); 
		 Thread.sleep(2000);
	 }
	 
	 @Test
	 void verifyResponseBody() {
		 System.out.println("response code is: "+ response.getStatusCode());
		 System.out.println("response body is: "+ response.getBody().asString());
		 Assert.assertEquals("status in the Response is: ", "success", response.jsonPath().getString("status"));
		 Assert.assertEquals(true, response.getBody().asString().contains(empName));
		 Assert.assertEquals(true, response.getBody().asString().contains(empSalary));
		 Assert.assertEquals(true, response.getBody().asString().contains(empAge));
	 }
	 
	 @Test
	 void verifyStatusCode() {
		 Assert.assertEquals("statusCode of the Response is: ", 200, response.getStatusCode());
	 }
	 
	 @Test(enabled=false)
	 void verifyStatusLine() {
		 Assert.assertEquals("statusCode of the Response is: ", "HTTP/1.1 200 OK", response.getStatusLine());
	 }
	 
	 @Test(enabled=true)
	 void parseJsonResponse() {
		 System.out.println("full response is: "+ response.getBody().asString());
		 System.out.println("**********************************************************");
		 System.out.println("status in response body is: "+ response.jsonPath().getString("status"));
		 System.out.println("**********************************************************");
		 System.out.println("data in response body is: " +response.path("data"));
		 System.out.println("**********************************************************");
		 System.out.println("Employee name in response body is: " +response.path("data.name"));
		 System.out.println("Employee salary in response body is: " +response.path("data.salary"));
		 System.out.println("Employee age in response body is: " +response.path("data.age"));
	
	 }
	 
	 @Test
	 void verifyHeaderContentType() {
		 System.out.println("Content-Type: "+ response.getHeader("Content-Type"));
		 Assert.assertEquals("Header ContentType in the Response is: ", "application/json;charset=utf-8", response.getHeader("Content-Type"));
	 }
	 
	 @Test
	 void verifyHeaderContentEncoding() {
		 System.out.println("Content-Encoding in response is: "+ response.getHeader("Content-Encoding"));
		 Assert.assertEquals("Header Content-Encoding in the Response is: ", "gzip", response.getHeader("Content-Encoding"));
	 }
 
	 @Test
	 void verifyHeaderServer() {
		 System.out.println("Header Server in response is: "+ response.getHeader("Server"));
		 Assert.assertEquals("Header Server in the Response is: ", "nginx/1.16.0", response.getHeader("Server"));
	 }

}
