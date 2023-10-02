package com.testautomation.apitesting.pojos;

public class Address {
	
	public String city;
	public String street;
    public String state;
    
    public Address() {}
    
    public Address(String city,String street,String state) {
    	setCity(city);
    	setStreet(street);
    	setState(state);
    }
    
    public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
