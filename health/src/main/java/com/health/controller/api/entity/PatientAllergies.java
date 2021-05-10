package com.health.controller.api.entity;

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

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PATIENTS_ALLERGIES")
@Getter
@Setter
public class PatientAllergies extends AbstractColumnDetails{


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@SequenceGenerator(name = "seq", sequenceName = "patients_allergies_id_seq", allocationSize = 1)
	@Column(name="ID",nullable = false)
	private Long id;
		
	@Column(name="ALLERGIES_DESC")
	private String description;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private Patients patients;

	public PatientAllergies(){}
	public PatientAllergies(Long id, String description, Patients patients,Long activeStatus,Long userId) {
		this.id = id;
		this.description = description;
		this.patients = patients;
		super.setActiveStatus(activeStatus);
		super.setCreatedBy(userId);
		super.setUpdatedBy(userId);
	}
	
	
	
	

}
