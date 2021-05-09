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
	private Long activeStatus;
	
	public PatientsDiagnosesResponse(Long id,Date diagnosesDate, Long diagnosesType, String description, String remarks,
			Long activeStatus) {
		this.id=id;
		this.diagnosesDate = diagnosesDate;
		this.diagnosesType = diagnosesType;
		this.description = description;
		this.remarks = remarks;
		this.activeStatus = activeStatus;
	}
	
	
}
