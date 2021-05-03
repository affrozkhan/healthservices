package com.health.controller.api.dataexchange.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientsAppointmentResponse {

	private Date dateOfAppointment;
	private String doctorName;
	private String mobileNumber;
	private String hospitalname;
	
	
	public PatientsAppointmentResponse(Date dateOfAppointment, String doctorName, String mobileNumber,
			String hospitalname) {
		super();
		this.dateOfAppointment = dateOfAppointment;
		this.doctorName = doctorName;
		this.mobileNumber = mobileNumber;
		this.hospitalname = hospitalname;
	}
	
	
	
	
}
