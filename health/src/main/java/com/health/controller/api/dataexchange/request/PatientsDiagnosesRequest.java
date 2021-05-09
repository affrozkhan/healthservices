package com.health.controller.api.dataexchange.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientsDiagnosesRequest {
	

	private Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date diagnosesDate;
	
	private Long diagnosesType;
	private String description;
	private String remarks;
	private Long activeStatus;
	
	
	
}
