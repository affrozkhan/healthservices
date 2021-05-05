package com.health.controller.api.dataexchange.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientsResponse {

	private String firstName;
	private String lastName;
	private String email;
	private String mobileNumber;
	private String address;
	private String bloodGroup;
	private String height;
	private String weight;
	private String userName;
	
	public PatientsResponse(String firstName, String lastName, String email, String mobileNumber, String address,
			String bloodGroup, String height, String weight, String userName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.bloodGroup = bloodGroup;
		this.height = height;
		this.weight = weight;
		this.userName = userName;
	}
	

	
	
	
	
}
