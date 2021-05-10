package com.health.controller.api.dataexchange.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.health.config.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientAppointmentsRequest {
	
	private Long id;
	private Long appointmentStatus;
	private String appointmentType;
	private String location;
	private String notes;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
	private Date startDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.TIME_FORMAT)
	private Date startTime;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
	private Date endDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern =Constants.TIME_FORMAT)
	private Date endTime;
	private Long doctorId;
	
	
	
	
}
