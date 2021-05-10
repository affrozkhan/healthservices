package com.health.controller.api.dataexchange.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.health.config.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientTestRequest {
	

		
	private Long id;
	private String testType;
	private String testDetails;
	private String notes;
	private String status;
	private String result;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
	private Date testDate;
	
	
	
	

	
	
}
