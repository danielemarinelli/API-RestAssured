package com.testautomation.apitesting.pojos;

import java.util.ArrayList;

public class Employees {

	//Converting the JSON request to a JAVA Class
	 	public String first_name;
	 	public String last_name;
	    public String email;
	    public String gender;
	    public ArrayList<String> phone;
	    public Skills skills;
	    public Address address;
	    
		public Employees() {
		}
		
		public Employees(String first_name,String last_name,String email,String gender,ArrayList<String> phone,Skills skills,Address address) {
			setFirst_name(first_name);
			setLast_name(last_name);
			setEmail(email);
			setGender(gender);
			setPhone(phone);
			setSkills(skills);
			setAddress(address);
		}
	    
	    public String getFirst_name() {
			return first_name;
		}

		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}

		public String getLast_name() {
			return last_name;
		}

		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}

		public ArrayList<String> getPhone() {
			return phone;
		}

		public void setPhone(ArrayList<String> phone) {
			this.phone = phone;
		}

		public Skills getSkills() {
			return skills;
		}

		public void setSkills(Skills skills) {
			this.skills = skills;
		}

		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		

		
}
