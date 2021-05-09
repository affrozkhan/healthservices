package com.health.controller.api.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PATIENTS")
@Getter
@Setter
public class Patients extends AbstractColumnDetails{

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@SequenceGenerator(name = "seq", sequenceName = "patients_id_seq", allocationSize = 1)
	@Column(name="ID",nullable = false)
	private Long id;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="email")
	private String email;

	@Column(name="mobile_number")
	private Long mobileNumber;

	@Column(name="address")
	private String address;

	@Column(name="blood_group")
	private String bloodGroup;

	@Column(name="height")
	private String height;

	@Column(name="weight")
	private String weight;

	@Column(name="sex")
	private String sex;

	@Column(name="dob")
	private Date dateOfBirth;

	@Column(name="PLACE_OF_BIRTH")
	private String placeOfBirth;

	@Column(name="OCCUPATION")
	private String occupation;

	@Column(name="PATIENT_STATUS")
	private String patientStatus;

	@Column(name="GUARDIAN_NAME")
	private String guardianName;

	@Column(name="GUARDIAN_RELATION")
	private String guardianRelation;

	@Column(name="GUARDIAN_MOBILE")
	private String guardianMobile;	

	@JsonIgnore
	@Column(name="user_role_id")
	private Long userRoleId;


	@OneToMany(mappedBy = "patients", orphanRemoval = true, cascade = CascadeType.ALL)
	public List<PatientDiagnoses> patientDiagnosesList;
	
	@OneToMany(mappedBy = "patients", orphanRemoval = true, cascade = CascadeType.ALL)
	public List<PatientAllergies> patientAllergiesList;

	@OneToMany(mappedBy = "patients", orphanRemoval = true, cascade = CascadeType.ALL)
	public List<PatientAppointments>appointmentsList;
	
	@OneToMany(mappedBy = "patients", orphanRemoval = true, cascade = CascadeType.ALL)
	public List<PatientMedications>patientMedicationsList;
	
	

	
	public Patients(){}
	
	public Patients(Long id, String firstName, String lastName, String email, Long mobileNumber, String address,
			String bloodGroup, String height, String weight, String sex, Date dateOfBirth, String placeOfBirth,
			String occupation, String patientStatus, String guardianName, String guardianRelation,
			String guardianMobile,Long activeStatus,Long userId) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.address = address;
		this.bloodGroup = bloodGroup;
		this.height = height;
		this.weight = weight;
		this.sex = sex;
		this.dateOfBirth = dateOfBirth;
		this.placeOfBirth = placeOfBirth;
		this.occupation = occupation;
		this.patientStatus = patientStatus;
		this.guardianName = guardianName;
		this.guardianRelation = guardianRelation;
		this.guardianMobile = guardianMobile;
		super.setActiveStatus(activeStatus);
		super.setCreatedBy(userId);
		super.setUpdatedBy(userId);
		
	}


	
	
	
}
