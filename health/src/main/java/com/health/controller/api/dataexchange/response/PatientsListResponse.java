package com.health.controller.api.dataexchange.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientsListResponse {
	
	private Long patientid;
	private String firstName;
	private String lastName;
	private String sex;
	private Date dateOfBirth;
	private String patientStatus;
	
	
	public PatientsListResponse(Long patientid, String firstName, String lastName, String sex, Date dateOfBirth,
			String patientStatus) {
		this.patientid = patientid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.dateOfBirth = dateOfBirth;
		this.patientStatus = patientStatus;
	}
	
	
	
	
	


	
	
	
	
}
