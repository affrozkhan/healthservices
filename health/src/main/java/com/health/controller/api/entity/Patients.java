package com.health.controller.api.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PATIENTS")
@Getter
@Setter
public class Patients extends AbstractColumnDetails{


	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="email")
	private String email;

	@Column(name="mobile_number")
	private String mobileNumber;

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

	@Id
	@JsonIgnore
	@Column(name="user_role_id")
	private Long userRoleId;

	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="USER_ROLES",
	joinColumns = {@JoinColumn(name="id", referencedColumnName="user_role_id",insertable=false,updatable=false)},
	inverseJoinColumns = {@JoinColumn(name="user_id", referencedColumnName="id",insertable=false,updatable=false)}
			)
	private Users user;


	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "patient_id")
	public List<PatientDiagnoses>patientDiagnosesList;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "patient_id")
	public List<PatientAllergies>patientAllergiesList;


}
