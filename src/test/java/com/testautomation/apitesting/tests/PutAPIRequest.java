package com.testautomation.apitesting.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.testautomation.apitesting.utils.FileNameConstants;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PutAPIRequest {
	
	@Test
	public void putAPIRequest() {
		
		try {
			
			String postAPIRequestBody = FileUtils.readFileToString(new File(FileNameConstants.POST_API_REQUEST_BODY),"UTF-8");
			
			String putAPIRequestBody = FileUtils.readFileToString(new File(FileNameConstants.PUT_API_REQUEST_BODY),"UTF-8");
			//System.out.println(postAPIRequestBody);
			//post api call
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
						
			int idPizza = JsonPath.read(response.body().asString(), "$.id");
			//get api call
		/*	RestAssured
				.given()
					.contentType(ContentType.JSON)
					.baseUri("http://localhost:3000/pizza")
				.when()
					.get("/{id}",idPizza)
				.then()
					.assertThat()
					.statusCode(200);
			*/
			//put api call
			RestAssured
				.given()
					.contentType(ContentType.JSON)
					.body(putAPIRequestBody)
					.baseUri("http://localhost:3000/pizza")
				.when()
					.put("/{id}",idPizza)
					.then()
					.assertThat()
					.statusCode(200)
					.body("type",Matchers.equalTo("Aragosta"));
					
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


}
