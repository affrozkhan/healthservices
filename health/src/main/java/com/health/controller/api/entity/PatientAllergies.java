package com.health.controller.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PATIENTS_ALLERGIES")
@Getter
@Setter
public class PatientAllergies extends AbstractColumnDetails{


	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@SequenceGenerator(name = "seq", sequenceName = "patients_allergies_id_seq", allocationSize = 1)
	@Column(name="ID",nullable = false)
	private Long id;
		
	@Column(name="ALLERGIES_DESC")
	private String description;
	
	@Column(name="patient_id")
	private Long patientId;
	

}