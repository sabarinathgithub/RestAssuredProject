package dataDrivenTesting;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.mastercard.mdcc.commonUtil.ExcelLib;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class DataDriven_Excel_AddNewEmp {
	
  @Test(dataProvider = "getData")
  public void createNewEmployee(String eName, String eSalary, String eAge) {
	  
	  RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
	  
	  RequestSpecification httpRequest = RestAssured.given();
	  
	  JSONObject requestParams = new JSONObject();
	  requestParams.put("name", eName);
	  requestParams.put("salary", eSalary);
	  requestParams.put("age", eAge);
	  
	  httpRequest.header("content-type", "application/json");
	  httpRequest.body(requestParams.toJSONString());
	  
	  Response response = httpRequest.request(Method.POST, "/create");
	  
	  System.out.println(response.getBody().asString());
	  Assert.assertEquals(response.getBody().asString().contains(eName), true);
	  Assert.assertEquals(response.getBody().asString().contains(eSalary), true);
	  Assert.assertEquals(response.getBody().asString().contains(eAge), true);
	  Assert.assertEquals(response.getStatusCode(), 200);
  }
  
  @DataProvider
  private String[][] getData() throws IOException{
	  String path = System.getProperty("user.dir")+"\\TestData.xlsx";
	  ExcelLib xlLib = new ExcelLib();
	  int rowCount = xlLib.getRowCount(path, "Sheet1");
	  int columnCount = xlLib.getCellCount(path, "Sheet1", 1);
	  System.out.println("Total number of rows is: "+ rowCount);
	  System.out.println("Total number of columns is: "+ columnCount);
	  
	  String[][] empDataSet = new String[rowCount][columnCount];
	  for(int i=1; i<=rowCount; i++) {
		  for(int j=0; j<columnCount; j++) {
			  empDataSet[i-1][j] = xlLib.getCellData(path, "Sheet1", i, j);
		  }
	  }
	  
	  //String[][] empDataSet = {{"name5", "10000", "25"}, {"name6", "20000", "26"}, {"name7", "30000", "27"}};
	  return empDataSet;
  }
}
