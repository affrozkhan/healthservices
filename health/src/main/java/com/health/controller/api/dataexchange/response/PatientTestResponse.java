package com.health.controller.api.dataexchange.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientTestResponse {
	
	private Long id;
	private String testType;
	private String testDetails;
	private String notes;
	private String status;
	private String result;
	
	public PatientTestResponse(Long id, String testType, String testDetails, String notes, String status,
			String result) {
		super();
		this.id = id;
		this.testType = testType;
		this.testDetails = testDetails;
		this.notes = notes;
		this.status = status;
		this.result = result;
	}
	
	
	
}
