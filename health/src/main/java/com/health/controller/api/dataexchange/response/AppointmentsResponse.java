package com.health.controller.api.dataexchange.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentsResponse {
	
	
	
	private String status;
	private String date;
	private String time;
	private String patient;
	private String doctor;
	
	public AppointmentsResponse(String status, String date, String time, String patient, String doctor) {
		this.status = status;
		this.date = date;
		this.time = time;
		this.patient = patient;
		this.doctor = doctor;
	}
	

	
	
	
}
