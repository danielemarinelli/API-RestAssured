package com.testautomation.apitesting.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.testautomation.apitesting.utils.BaseTest;
import com.testautomation.apitesting.utils.FileNameConstants;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONArray;

public class PostAPIRequestUsingFile extends BaseTest {
	
	@Test
	public void postAPIRequest() {
		
		try {
			
			String postAPIRequestBody = FileUtils.readFileToString(new File(FileNameConstants.POST_API_REQUEST_BODY),"UTF-8");
			//System.out.println(postAPIRequestBody);
			
			Response response = 
			RestAssured
				.given()
					.contentType(ContentType.JSON)
					.body(postAPIRequestBody)  //send body from file
					.baseUri("http://localhost:3000/pizza")
				.when()
					.post()
				.then()
					.assertThat()
					.statusCode(201)
				.extract()
					.response();
			
			// checking JSON ONLINE EVALUATOR to find the JsonPath
			String pizzaType = JsonPath.read(response.body().asString(), "$.type");
			System.out.println(pizzaType);
			//Validation of the API response fields sent from the endpoint
			Assert.assertEquals(pizzaType, "Onion frozen");
			String glovoOption = JsonPath.read(response.body().asString(), "$.takeaway.glovo");
			Assert.assertEquals(glovoOption, "no");
			
			int idPizza = JsonPath.read(response.body().asString(), "$.id");
			System.out.println(idPizza);
			RestAssured
				.given()
					.contentType(ContentType.JSON)
					.baseUri("http://localhost:3000/pizza")
				.when()
					.get("/{id}",idPizza)
				.then()
					.assertThat()
					.statusCode(200);
			
					
					
			// training at 1h 53min
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
