package com.health.controller.api.dataexchange.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientAllergiesResponse {
	

	private String description;
	
	public PatientAllergiesResponse(String description) {
		this.description = description;
	}
	
	
}
