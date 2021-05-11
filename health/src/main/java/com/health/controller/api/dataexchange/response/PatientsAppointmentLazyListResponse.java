package com.health.controller.api.dataexchange.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientsAppointmentLazyListResponse {
	
	private Long totalCount;
	private Long rowCount;
	private List<PatientsAppointmentListResponse>appointmentList;
	
	public PatientsAppointmentLazyListResponse(Long totalCount, Long rowCount, List<PatientsAppointmentListResponse> appointmentList) {
		this.totalCount = totalCount;
		this.rowCount = rowCount;
		this.appointmentList = appointmentList;
	}

	public PatientsAppointmentLazyListResponse() {
	}
	
	
	
	
	
	
	


	
	
	
	
}
