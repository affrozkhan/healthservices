package com.health.controller.api.dataexchange.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientsDiagnosesResponse {
	
	private Long id;
	private Date diagnosesDate;
	private Long diagnosesType;
	private String description;
	private String remarks;
	
	public PatientsDiagnosesResponse(Long id,Date diagnosesDate, Long diagnosesType, String description, 
			String remarks) {
		this.id=id;
		this.diagnosesDate = diagnosesDate;
		this.diagnosesType = diagnosesType;
		this.description = description;
		this.remarks = remarks;
	}
	
	
}
