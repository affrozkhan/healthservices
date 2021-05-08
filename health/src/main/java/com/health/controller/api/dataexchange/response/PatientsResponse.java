package com.health.controller.api.dataexchange.response;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientsResponse {
	
	private String userName;
	private String firstName;
	private String lastName;
	private String patientStatus;
	private String sex;
	private Date dateOfBirth;
	private String bloodGroup;
	private String height;
	private String weight;
	private String placeOfBirth;
	private String occupation;
	private String email;
	private String mobileNumber;
	private String address;
	private String guardianName;
	private String guardianRelation;
	private String guardianMobile;
	public List<PatientsDiagnosesResponse>patientDiagnosesList;
	public List<PatientAllergiesResponse>patientAllergiesList;
	
	
	
	public PatientsResponse(String userName, String firstName, String lastName, String patientStatus, String sex,
			Date dateOfBirth, String bloodGroup, String height, String weight, String placeOfBirth, String occupation,
			String email, String mobileNumber, String address, String guardianName, String guardianRelation,
			String guardianMobile,List<PatientsDiagnosesResponse>patientDiagnosesList,List<PatientAllergiesResponse>patientAllergiesList) {
		this.userName = userName;
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
	}	
	


	
	
	
	
}
