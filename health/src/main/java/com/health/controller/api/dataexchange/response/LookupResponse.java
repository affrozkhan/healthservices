package com.health.controller.api.dataexchange.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LookupResponse {
	
	
	
	private Long key;
	private String description;
	
	public LookupResponse(Long key, String description) {
		this.key = key;
		this.description = description;
	}
	
	
	

	
	
	
}
