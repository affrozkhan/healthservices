package com.health.controller.api.dataexchange.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientsAppointmentsResponse {
	
	private Long id;
	private Long appointmentStatus;
	private String appointmentType;
	private String location;
	private String notes;
	private Date startDate;
	private Date startTime;
	private Date endDate;
	private Date endTime;
	private Long doctorId;
	
	public PatientsAppointmentsResponse(Long id, Long appointmentStatus, String appointmentType, String location,
			String notes, Date startDate, Date startTime, Date endDate, Date endTime, Long doctorId) {
		this.id = id;
		this.appointmentStatus = appointmentStatus;
		this.appointmentType = appointmentType;
		this.location = location;
		this.notes = notes;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		this.doctorId = doctorId;
	}
	
	
	
	
}
