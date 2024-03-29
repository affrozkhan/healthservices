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
@Table(name = "patients_tests")
@Getter
@Setter
public class PatientTests extends AbstractColumnDetails {
	
		
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patients_tests_id_seq")
	@SequenceGenerator(name = "patients_tests_id_seq", sequenceName = "patients_tests_id_seq", allocationSize = 1)
	@Column(name="ID",nullable = false)
	private Long id;

		
	@Column(name="test_type")
	private String testType;
	
	@Column(name="test_details")
	private String testDetails;
	
	
	@Column(name="notes")
	private String notes;
	
	@Column(name="test_status")
	private String status;
	
	@Column(name="test_result")
	private String result;
	
	@Column(name="test_date")
	private Date testDate;
	
	
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patients_appointment_id")
	@NotFound(action=NotFoundAction.IGNORE)
	private PatientAppointments patientAppointments;
	
	
	public PatientTests(){}
	
	
	
	

	public PatientTests(Long id, String testType, String testDetails, String notes, String status, String result,Date testDate,
			PatientAppointments patientAppointments,Long activeStatus,Long userId) {
		super();
		this.id = id;
		this.testType = testType;
		this.testDetails = testDetails;
		this.notes = notes;
		this.status = status;
		this.result = result;
		this.testDate=testDate;
		this.patientAppointments = patientAppointments;
		super.setActiveStatus(activeStatus);
		super.setCreatedBy(userId);
		super.setUpdatedBy(userId);
	}


	

	
	
}
