package com.testautomation.apitesting.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.logging.log4j.core.lookup.MainMapLookup;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonschema.main.cli.Main;
import com.testautomation.apitesting.listener.RestAssuredListener;
import com.testautomation.apitesting.pojos.Pizza;
import com.testautomation.apitesting.pojos.Takeaway;
import com.testautomation.apitesting.utils.FileNameConstants;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DataDrivenTestingUsingExcelFile {
	
  @Test (dataProvider = "ExcelTestData")
  public void DataDrivenTesting(Map<String,String> testData) throws JsonProcessingException {
	  
	  System.out.println(testData.get("type"));
	  int totalPizzaPrice= Integer.parseInt(testData.get("cost"));
		Takeaway takea = new Takeaway("yes","yes");
		Pizza pizza = new Pizza(totalPizzaPrice,testData.get("type"), takea);
	
		//serialization
		ObjectMapper om = new ObjectMapper();
		String requestBody = om.writerWithDefaultPrettyPrinter().writeValueAsString(pizza);
		
		//System.out.println(requestBody);
			
		Response response =
		RestAssured
		.given().filter(new RestAssuredListener())
			.contentType(ContentType.JSON)
			.body(requestBody) 
			.baseUri("http://localhost:3000/pizza")
		.when()
			.post()
		.then()
			.assertThat()
			.statusCode(201)
		.extract()
			.response();
  }
  
  @DataProvider(name="ExcelTestData")
  public Object[][] getTestData(){
	  
	  String query = "select * from pizzasheet where Run='yes'";  //pizzasheet is the sheet name in EXCEL FILE
	  
	  Object[][] objArray =null;  //Array will contain all the rows of the excel sheet one at the time
	  Map<String,String> testData = null;
	  List<Map<String,String>> testDataList = null;   // List will contain all the rows of the excel sheet in a list
	  
	  Fillo fillo = new Fillo();
	  Connection connection = null;
	  Recordset recordset = null;
	  
	  try {
		  connection= fillo.getConnection(FileNameConstants.EXCEL_TEST_DATA);
		recordset = connection.executeQuery(query);
		
		testDataList = new ArrayList<Map<String,String>>();
		
		while(recordset.next()) {
			testData = new TreeMap<String,String>(String.CASE_INSENSITIVE_ORDER);
			
			for (String field : recordset.getFieldNames()) {
				testData.put(field, recordset.getField(field));
			}
			
			testDataList.add(testData);
		}
		
		objArray = new Object[testDataList.size()][1];
		
		for(int i =0; i < testDataList.size(); i++) {
			objArray[i][0] = testDataList.get(i);
		}
		
	} catch (FilloException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	  
	  return objArray;
  }
  
}






