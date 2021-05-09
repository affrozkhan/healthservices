package com.health.controller.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PATIENTS_DIAGNOSES")
@Getter
@Setter
public class PatientDiagnoses extends AbstractColumnDetails{


	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@SequenceGenerator(name = "seq", sequenceName = "patients_diagnoses_id_seq", allocationSize = 1)
	@Column(name="ID",nullable = false)
	private Long id;
	
	@Column(name="diagnoses_date")
	private Date diagnosesDate;
	
	@Column(name="diagnoses_type")
	private Long diagnosesType;
	
	@Column(name="description")
	private String description;

	@Column(name="remarks")
	private String remarks;
	
	/*@NotFound(action=NotFoundAction.IGNORE)
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	private Patients patients;
	*/
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private Patients patients;
	
	

	
	

	
	public PatientDiagnoses(){}
	public PatientDiagnoses(Long id, Date diagnosesDate, Long diagnosesType, String description, String remarks,
			Patients patients,Long activeStatus,Long userId) {
		this.id = id;
		this.diagnosesDate = diagnosesDate;
		this.diagnosesType = diagnosesType;
		this.description = description;
		this.remarks = remarks;
		this.patients = patients;
		super.setActiveStatus(activeStatus);
		super.setCreatedBy(userId);
		super.setUpdatedBy(userId);
	}
	
	
	
	

}
