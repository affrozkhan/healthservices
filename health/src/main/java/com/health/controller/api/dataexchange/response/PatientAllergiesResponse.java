package com.health.controller.api.dataexchange.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientAllergiesResponse {
	
	private Long id;
	private String description;
	
	public PatientAllergiesResponse(Long id,String description) {
		this.id=id;
		this.description = description;
	}
	
	
}
