package com.health.controller.api.dataexchange.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorResponse {

	private String firstName;
	private String lastName;
	private String email;
	private String mobileNumber;
	private String address;
	private String userName;
	
	public DoctorResponse(String firstName, String lastName, String email, String mobileNumber, String address,
			String userName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.userName = userName;
	}
	
	
	
	
}
