package com.health.controller.api.dataexchange.request;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

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

	@JsonFormat(pattern = Constants.TIME_FORMAT)
	private LocalTime startTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
	private Date endDate;

	@JsonFormat(pattern = Constants.TIME_FORMAT)
	private LocalTime endTime;
	
	
	private Long doctorId;
	
	public List<PatientMedicationsRequest>patientMedicationsList;

	public List<PatientTestRequest> patientTestsList;
	

	
}
