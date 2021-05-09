package com.health.controller.api.dataexchange.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientsLazyListResponse {
	
	private Long totalCount;
	private Long rowCount;
	private List<PatientsListResponse>patientList;
	
	public PatientsLazyListResponse(Long totalCount, Long rowCount, List<PatientsListResponse> patientList) {
		this.totalCount = totalCount;
		this.rowCount = rowCount;
		this.patientList = patientList;
	}

	public PatientsLazyListResponse() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	
	


	
	
	
	
}
