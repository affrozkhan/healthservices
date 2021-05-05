package com.health.controller.api.dataexchange.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponse {

	private String message;
	private String code;
	
	public MessageResponse(String message, String code) {
		this.message = message;
		this.code = code;
	}
	
	
	
}
