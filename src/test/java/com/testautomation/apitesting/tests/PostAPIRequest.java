package com.testautomation.apitesting.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class PostAPIRequest {
	
	@Test
	public void createPizza() {
	
		
		//prepare request body
		JSONObject pizza = new JSONObject();
		JSONObject takeAway = new JSONObject();
		pizza.put("type", "Acciughe & Prosciutto");
		pizza.put("cost", 14);
		pizza.put("takeaway", takeAway);
		takeAway.put("online", "yes");
		takeAway.put("glovo", "yes");
		
		Response resp =
		RestAssured
			.given()
				.contentType(ContentType.JSON)
				.body(pizza.toString())
				.baseUri("http://localhost:3000/pizza")
				//.log().body()  //prints on the console the request body that we are sending with payload
				//.log().headers()  //prints on the console the headers that we are sending 
				//log().all()
			.when()
				.post()
			.then()
				.assertThat()
				//.log().ifValidationFails()
				.statusCode(201)
				.body("type", Matchers.equalTo("Acciughe & Prosciutto"))
				.body("cost", Matchers.equalTo(14))
			.extract()
				.response();
		
		int pizzaId = resp.path("id");
		System.out.println(pizzaId);
		
		//Validation of the response giving the pizzaID as parameter
		
		RestAssured
				.given()
					.contentType(ContentType.JSON)
					.pathParam("id", pizzaId)
					.baseUri("http://localhost:3000/pizza")
				.when()
					.get("{id}")
				.then()
					.assertThat()
					.statusCode(200)
					.body("type", Matchers.equalTo("Acciughe & Prosciutto"));
		
			
	}
              
	

}
