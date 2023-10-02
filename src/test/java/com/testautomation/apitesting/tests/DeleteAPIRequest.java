package com.testautomation.apitesting.tests;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DeleteAPIRequest {
	
	
	@Test
	public void pizzaDelete() {
		
		//delete api call
		RestAssured
		.given()
			.contentType(ContentType.JSON)
			.baseUri("http://localhost:3000/pizza")
		.when()
			.delete("/49")
			//.delete("/{id}",pizzaId)
		.then()
			.assertThat()
			.statusCode(200);
		System.out.println("Deleted pizza process ok");
	}

}
