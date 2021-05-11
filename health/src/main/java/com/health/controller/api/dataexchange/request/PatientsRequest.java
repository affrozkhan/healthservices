package com.health.controller.api.dataexchange.request;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.health.config.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientsRequest {
	
	
	private Long patientid;
	private String firstName;
	private String lastName;
	private String patientStatus;
	private String sex;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
	private Date dateOfBirth;
	
	private String bloodGroup;
	private String height;
	private String weight;
	private String placeOfBirth;
	private String occupation;
	private String email;
	private Long mobileNumber;
	private String address;
	private String guardianName;
	private String guardianRelation;
	private Long guardianMobile;
	public List<PatientsDiagnosesRequest>patientDiagnosesList;
	public List<PatientAllergiesRequest>patientAllergiesList;
	public List<PatientAppointmentsRequest>patientAppointmentsList;
	
	
	private Long userId;
	
	
	
	
	
	

	
	
	
	
}
