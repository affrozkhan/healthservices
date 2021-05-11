package com.health.controller.api.dataexchange.response;

import java.time.LocalTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.health.config.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientsAppointmentListResponse {
	
	private Long id;
	private Long status;
	private String type;
	private String location;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
	private Date startDate;
	
	@JsonFormat(pattern = Constants.TIME_FORMAT)
	private LocalTime  startTime;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
	private Date endDate;
	
	@JsonFormat(pattern = Constants.TIME_FORMAT)
	private LocalTime  endTime;
	
	private String patientName;
	private Long doctorId;
	
	public PatientsAppointmentListResponse(Long id, Long status, String type, String location, Date startDate,
			LocalTime startTime, Date endDate, LocalTime endTime, String patientName, Long doctorId) {
		this.id = id;
		this.status = status;
		this.type = type;
		this.location = location;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		this.patientName = patientName;
		this.doctorId = doctorId;
	}
	
	
	
	
	
	
	
	


	
	
	
	
}
