package com.testautomation.apitesting.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.testautomation.apitesting.listener.RestAssuredListener;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetAPIRequest {
	
	@Test
	public void getAllPizzas(){
		
		Response resp =
		RestAssured
				.given().filter(new RestAssuredListener())
					.contentType(ContentType.JSON)
					.baseUri("http://localhost:3000/pizza")
				.when()
					.get()
				.then()
					.assertThat()
					.statusCode(200)
					.statusLine("HTTP/1.1 200 OK")
					.header("Content-Type", "application/json; charset=utf-8")
				.extract()
					.response();
		System.out.println(resp.getBody().asString());
		Assert.assertTrue(resp.getBody().asString().contains("id"));
	}

}
