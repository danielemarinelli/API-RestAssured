package com.testautomation.apitesting.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testautomation.apitesting.pojos.Pizza;
import com.testautomation.apitesting.pojos.Takeaway;
import com.testautomation.apitesting.utils.FileNameConstants;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class PostAPIRequestUsingPojos {
	
	@Test
	public void postAPIRequest() {
		
		try {
			
			String jsonschema = FileUtils.readFileToString(new File(FileNameConstants.JSON_SCHEMA),"UTF-8");
		
			Takeaway takea = new Takeaway("yes","yes");
			Pizza pizza = new Pizza(13,"Prosciutto", takea);
			
			//serialization
			ObjectMapper om = new ObjectMapper();
			String requestBody = om.writerWithDefaultPrettyPrinter().writeValueAsString(pizza);
			
			System.out.println(requestBody);
			
			//de-serialization
			Pizza pizzaDetails = om.readValue(requestBody, Pizza.class);
			System.out.println(pizzaDetails.getType());
			System.out.println(pizzaDetails.getTakeaway().getGlovo());
			
			Response response =
			RestAssured
			.given()
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
			
			int pizzaId = response.path("id");
				//Verify if the resource is present or not in the DB (after creating it with POST
			//System.out.println(jsonschema);
			
			RestAssured
			.given()
				.contentType(ContentType.JSON)
				.body(requestBody)  
				.baseUri("http://localhost:3000/pizza")
			.when()
				.get("/{id}", pizzaId)
			.then()
				.assertThat()
				.statusCode(200)
				// do fields accept proper type of values such as string , integer....
				.body(JsonSchemaValidator.matchesJsonSchema(jsonschema));  //this will validate the JSON SCHEMA
			
		
			// API RESPONSE HAS THE API OBJECT, LETS VERIFY THAT the response has the proper values
			// SCHEMA CHECK -- CHECK IF THE FIELD ACCEPTS THE CORRECT TYPE es. INT OR STRING
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
