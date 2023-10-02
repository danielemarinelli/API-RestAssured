package com.testautomation.apitesting.pojos;

public class Skills {
		
	
	public String name;
	public String proficiency;
    public int java_level;
    
    public Skills() {}
    
    public Skills(String name,String proficiency,int java_level) {
    	setName(name);
    	setProficiency(proficiency);
    	setJava_level(java_level);
    }
    
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProficiency() {
		return proficiency;
	}
	public void setProficiency(String proficiency) {
		this.proficiency = proficiency;
	}
	public int getJava_level() {
		return java_level;
	}
	public void setJava_level(int java_level) {
		this.java_level = java_level;
	}
	
    
    
    
}
