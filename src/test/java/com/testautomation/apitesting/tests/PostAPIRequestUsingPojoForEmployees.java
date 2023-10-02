package com.testautomation.apitesting.tests;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testautomation.apitesting.pojos.Address;
import com.testautomation.apitesting.pojos.Employees;
import com.testautomation.apitesting.pojos.Skills;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class PostAPIRequestUsingPojoForEmployees {
	
	@Test
	public void postAPIRequest() {
		
		Skills skillSet = new Skills("Testing","Excellent",28);
		Address addressEmployee = new Address("Cincinnati","Fairways blvd","OH");
		ArrayList<String> phones = new ArrayList<>();
		phones.add("3419000934");
		phones.add("0619700934");
		Employees empl = new Employees("Edo","Mari","em@coccinella.eu.it","Male",phones,skillSet,addressEmployee);
		
		
		//serialization
		ObjectMapper om = new ObjectMapper();
		try {
			String requestBody = om.writerWithDefaultPrettyPrinter().writeValueAsString(empl);
			System.out.println(requestBody);
			
			//de-serialization
			Employees employeeDetails = om.readValue(requestBody, Employees.class);
			System.out.println(employeeDetails.getPhone());
			System.out.println(employeeDetails.getAddress().getCity());
			
			Response response =
					RestAssured
					.given()
						.contentType(ContentType.JSON)
						.body(requestBody) 
						.baseUri("http://localhost:3000/employees")
					.when()
						.post()
					.then()
						.assertThat()
						.statusCode(201)
					.extract()
						.response();
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
