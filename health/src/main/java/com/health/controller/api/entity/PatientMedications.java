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
@Table(name = "patients_medication")
@Getter
@Setter
public class PatientMedications extends AbstractColumnDetails {
	
		
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@SequenceGenerator(name = "seq", sequenceName = "patients_medication_id_seq", allocationSize = 1)
	@Column(name="ID",nullable = false)
	private Long id;

		
	@JsonIgnore
	@Column(name="Medication")
	private String medication;
	
	@JsonIgnore
	@Column(name="Prescription")
	private String prescription;
	
	@JsonIgnore
	@Column(name="Prescription_date")
	private Date prescriptionDate;
	
	@JsonIgnore
	@Column(name="bill_to")
	private String billTo;
	
	@JsonIgnore
	@Column(name="quantity_dispensed")
	private String quantity;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private Patients patients;
	
	
	@JsonIgnore
	@Column(name="doctor_id")
	private Long doctorId;


	public PatientMedications(Long id, String medication, String prescription, Date prescriptionDate, String billTo,
			String quantity, Patients patients, Long doctorId,Long activeStatus,Long userId) {
		super();
		this.id = id;
		this.medication = medication;
		this.prescription = prescription;
		this.prescriptionDate = prescriptionDate;
		this.billTo = billTo;
		this.quantity = quantity;
		this.patients = patients;
		this.doctorId = doctorId;
		super.setActiveStatus(activeStatus);
		super.setCreatedBy(userId);
		super.setUpdatedBy(userId);
	}


	

	
	
}
