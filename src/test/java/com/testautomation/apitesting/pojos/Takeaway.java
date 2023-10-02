package com.testautomation.apitesting.pojos;

public class Takeaway {
	
	private String online;
	private String glovo;
	
	public Takeaway() {}
	
	public Takeaway(String online, String glovo) {
		setOnline(online);
		setGlovo(glovo);
	}
	
	public String getOnline() {
		return online;
	}
	public void setOnline(String online) {
		this.online = online;
	}
	public String getGlovo() {
		return glovo;
	}
	public void setGlovo(String glovo) {
		this.glovo = glovo;
	}
	

}
