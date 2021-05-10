package com.health.controller.api.dataexchange.response;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.health.config.Constants;

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

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
	private Date startDate;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.TIME_FORMAT)
	private Date startTime;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
	private Date endDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.TIME_FORMAT)
	private Date endTime;
	
	private Long doctorId;
	
	public List<PatientMedicationsResponse>patientMedicationsList;
	
	public List<PatientTestResponse>patientTestsList;

	

	
	public PatientsAppointmentsResponse(Long id, Long appointmentStatus, String appointmentType, String location,
			String notes, Date startDate, Date startTime, Date endDate, 
			Date endTime, Long doctorId,
			List<PatientMedicationsResponse>patientMedicationsList,
			List<PatientTestResponse>patientTestsList) {
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
		this.patientMedicationsList=patientMedicationsList;
		this.patientTestsList=patientTestsList;
	}
	
	
	
	
}
