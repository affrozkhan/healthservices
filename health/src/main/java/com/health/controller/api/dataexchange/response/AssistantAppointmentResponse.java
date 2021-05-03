package com.health.controller.api.dataexchange.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssistantAppointmentResponse {

	private String patientname;
	private Date dateOfAppointment;
	private String doctorName;
	private String mobileNumber;
	private String hospital;
	
	public AssistantAppointmentResponse(String patientname, Date dateOfAppointment, String doctorName,
			String mobileNumber, String hospital) {
		super();
		this.patientname = patientname;
		this.dateOfAppointment = dateOfAppointment;
		this.doctorName = doctorName;
		this.mobileNumber = mobileNumber;
		this.hospital = hospital;
	}
	
	
	
	
}
