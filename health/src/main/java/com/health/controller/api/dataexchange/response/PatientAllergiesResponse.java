package com.health.controller.api.dataexchange.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientAllergiesResponse {
	
	private Long id;
	private String description;
	private Long activeStatus;
	
	public PatientAllergiesResponse(Long id,String description,Long activeStatus) {
		this.id=id;
		this.description = description;
		this.activeStatus=activeStatus;
	}
	
	
}
