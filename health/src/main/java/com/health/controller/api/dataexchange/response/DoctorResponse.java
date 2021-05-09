package com.health.controller.api.dataexchange.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorResponse {

	private Long  doctorid;
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNumber;
	
	public DoctorResponse(Long doctorid, String firstName, String lastName, String email, String mobileNumber) {
		this.doctorid = doctorid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
	}
	
	
	
	
	
	
}
