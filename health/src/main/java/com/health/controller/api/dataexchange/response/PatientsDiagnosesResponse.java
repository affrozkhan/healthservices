package com.health.controller.api.dataexchange.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientsDiagnosesResponse {
	

	private Date diagnosesDate;
	private Long diagnosesType;
	private String description;
	private String remarks;
	private Long active;
	
	public PatientsDiagnosesResponse(Date diagnosesDate, Long diagnosesType, String description, String remarks,
			Long active) {
		super();
		this.diagnosesDate = diagnosesDate;
		this.diagnosesType = diagnosesType;
		this.description = description;
		this.remarks = remarks;
		this.active = active;
	}
	
	
}
