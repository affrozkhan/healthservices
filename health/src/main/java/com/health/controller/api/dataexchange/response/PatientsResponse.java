package com.health.controller.api.dataexchange.response;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.health.config.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientsResponse {
	
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
	public List<PatientsDiagnosesResponse>patientDiagnosesList;
	public List<PatientAllergiesResponse>patientAllergiesList;
	public List<PatientsAppointmentsResponse>patientAppointmentsList;
	public List<PatientMedicationsResponse>patientMedicationsList;
	
	
	
	
	
	
	
	public PatientsResponse(Long patientid, String firstName, String lastName, String patientStatus, String sex,
			Date dateOfBirth, String bloodGroup, String height, String weight, String placeOfBirth, String occupation,
			String email, Long mobileNumber, String address, String guardianName, String guardianRelation,
			Long guardianMobile,List<PatientsDiagnosesResponse>patientDiagnosesList,
			List<PatientAllergiesResponse>patientAllergiesList,
			List<PatientsAppointmentsResponse>patientAppointmentsList,
			List<PatientMedicationsResponse>patientMedicationsList) {
		this.patientid = patientid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.patientStatus = patientStatus;
		this.sex = sex;
		this.dateOfBirth = dateOfBirth;
		this.bloodGroup = bloodGroup;
		this.height = height;
		this.weight = weight;
		this.placeOfBirth = placeOfBirth;
		this.occupation = occupation;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.guardianName = guardianName;
		this.guardianRelation = guardianRelation;
		this.guardianMobile = guardianMobile;
		this.patientDiagnosesList=patientDiagnosesList;
		this.patientAllergiesList=patientAllergiesList;
		this.patientAppointmentsList=patientAppointmentsList;
		this.patientMedicationsList=patientMedicationsList;

	}	
	


	
	
	
	
}
