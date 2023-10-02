package com.testautomation.apitesting.pojos;

public class Pizza {
	//Converting the JSON request to a JAVA Class
	private int cost;
	private String type;
	private Takeaway takeaway;

	public Pizza() {
	}
	
	public Pizza(int cost,String type, Takeaway takeaway) {
		setCost(cost);
		setType(type);
		setTakeaway(takeaway);
	}
	
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public Takeaway getTakeaway() {
		return takeaway;
	}

	public void setTakeaway(Takeaway takeaway) {
		this.takeaway = takeaway;
	}

}
