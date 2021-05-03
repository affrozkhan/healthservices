package com.health.controller.api.dataexchange.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

	private String userName;
	private String fullName;
	private Date dateOfBirth;
	private String address;
	private String mobileNumber;
	
}
