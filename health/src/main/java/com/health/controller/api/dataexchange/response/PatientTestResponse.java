package com.health.controller.api.dataexchange.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.health.config.Constants;

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
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
	private Date testDate;
	
	public PatientTestResponse(Long id, String testType, String testDetails, String notes, String status,
			String result,Date testDate) {
		super();
		this.id = id;
		this.testType = testType;
		this.testDetails = testDetails;
		this.notes = notes;
		this.status = status;
		this.result = result;
		this.testDate=testDate;
	}
	
	
	
}
