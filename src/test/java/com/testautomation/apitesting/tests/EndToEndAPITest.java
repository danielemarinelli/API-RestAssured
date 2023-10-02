package com.testautomation.apitesting.tests;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;
import com.testautomation.apitesting.listener.RestAssuredListener;
import com.testautomation.apitesting.utils.BaseTest;
import com.testautomation.apitesting.utils.FileNameConstants;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class EndToEndAPITest extends BaseTest {
	
	private static final Logger logger = LogManager.getLogger(EndToEndAPITest.class);
	
	@Test
	public void e2eAPIRequest() {
		
		logger.info("e2e API test execution started...");
		
		try {
			
			String postAPIRequestBody = FileUtils.readFileToString(new File(FileNameConstants.POST_API_REQUEST_BODY),"UTF-8");
			
			String putAPIRequestBody = FileUtils.readFileToString(new File(FileNameConstants.PUT_API_REQUEST_BODY),"UTF-8");
			
			String patchAPIRequestBody = FileUtils.readFileToString(new File(FileNameConstants.PATCH_API_REQUEST_BODY),"UTF-8");
			//post api call
			Response response = 
			RestAssured
				.given().filter(new RestAssuredListener())
					.contentType(ContentType.JSON)
					.body(postAPIRequestBody)  //send body from file
					.baseUri("http://localhost:3000/pizza")
				.when()
					.post()
				.then()
					.assertThat()
					.statusCode(101)
				.extract()
					.response();
			
			// checking JSON ONLINE EVALUATOR to find the JsonPath
			String pizzaType = JsonPath.read(response.body().asString(), "$.type");
						
			int idPizza = JsonPath.read(response.body().asString(), "$.id");
			//get api call
			RestAssured
				.given().filter(new RestAssuredListener())
					.contentType(ContentType.JSON)
					.baseUri("http://localhost:3000/pizza")
				.when()
					.get("/{id}",idPizza)
				.then()
					.assertThat()
					.statusCode(200);
			
			//put api call
			RestAssured
				.given().filter(new RestAssuredListener())
					.contentType(ContentType.JSON)
					.body(putAPIRequestBody)
					.baseUri("http://localhost:3000/pizza")
				.when()
					.put("/{id}",idPizza)
				.then()
					.assertThat()
					.statusCode(200)
					.body("type",Matchers.equalTo("Aragosta"));
				
			//patch api call
			RestAssured
				.given().filter(new RestAssuredListener())
					.contentType(ContentType.JSON)
					.body(patchAPIRequestBody)
					.baseUri("http://localhost:3000/pizza")
				.when()
					.patch("/{id}",idPizza)
				.then()
					.assertThat()
					.statusCode(200)
					.body("type",Matchers.equalTo("Zucchine&sausage"));
			
			//delete api call
			RestAssured
			.given().filter(new RestAssuredListener())
				.contentType(ContentType.JSON)
				.baseUri("http://localhost:3000/pizza")
			.when()
				.delete("/{id}",idPizza)
			.then()
				.assertThat()
				.statusCode(200);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.info("e2e API test execution ended...");
	}

}
