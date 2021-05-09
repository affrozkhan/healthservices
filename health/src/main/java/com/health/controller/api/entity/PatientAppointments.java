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
@Table(name = "PATIENTS_APPOINTMENT")
@Getter
@Setter
public class PatientAppointments extends AbstractColumnDetails {
	
		
	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@SequenceGenerator(name = "seq", sequenceName = "patients_appointment_id_seq", allocationSize = 1)
	@Column(name="ID",nullable = false)
	private Long id;

	@Column(name="appointment_status")
	private Long status;

	@JsonIgnore
	@Column(name="appointment_type")
	private String type;
	
	@JsonIgnore
	@Column(name="location")
	private String location;
	
	
	@JsonIgnore
	@Column(name="notes")
	private String notes;
	
	
	@JsonIgnore
	@Column(name="start_date")
	private Date startDate;
	
	@JsonIgnore
	@Column(name="start_time")
	private Date startTime;
	
	@JsonIgnore
	@Column(name="end_date")
	private Date endDate;
	
	@JsonIgnore
	@Column(name="end_time")
	private Date endTime;
	
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private Patients patients;
	
	
	@JsonIgnore
	@Column(name="doctor_id")
	private Long doctorId;


	public PatientAppointments(Long id, Long status, String type, String location, String notes, Date startDate,
			Date startTime, Date endDate, Date endTime, Patients patients, Long doctorId,Long activeStatus,Long userId) {
		this.id = id;
		this.status = status;
		this.type = type;
		this.location = location;
		this.notes = notes;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		this.patients = patients;
		this.doctorId = doctorId;
		super.setActiveStatus(activeStatus);
		super.setCreatedBy(userId);
		super.setUpdatedBy(userId);
	}
	

	
	
}
