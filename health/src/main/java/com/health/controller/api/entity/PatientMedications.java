package com.health.controller.api.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "patients_medication")
@Getter
@Setter
public class PatientMedications extends AbstractColumnDetails {
	
		
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@SequenceGenerator(name = "seq", sequenceName = "patients_medication_id_seq", allocationSize = 1)
	@Column(name="ID",nullable = false)
	private Long id;

		
	@Column(name="Medication")
	private String medication;
	
	@Column(name="Prescription")
	private String prescription;
	
	@Column(name="Prescription_date")
	private Date prescriptionDate;
	
	@Column(name="bill_to")
	private String billTo;
	
	@Column(name="quantity_dispensed")
	private String quantity;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patients_appointment_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private PatientAppointments patientAppointments;
	
	
	public PatientMedications(){}
	public PatientMedications(Long id, String medication, String prescription, Date prescriptionDate, String billTo,
			String quantity,PatientAppointments patientAppointments,Long activeStatus,Long userId) {
		super();
		this.id = id;
		this.medication = medication;
		this.prescription = prescription;
		this.prescriptionDate = prescriptionDate;
		this.billTo = billTo;
		this.quantity = quantity;
		this.patientAppointments = patientAppointments;
		super.setActiveStatus(activeStatus);
		super.setCreatedBy(userId);
		super.setUpdatedBy(userId);
	}


	

	
	
}
