package com.health.controller.api.dataexchange.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientMedicationsResponse {
	
	
	private Long id;
	private String medication;
	private String prescription;
	private Date prescriptionDate;
	private String billTo;
	private String quantity;
	private Long doctorId;
	
	public PatientMedicationsResponse(Long id, String medication, String prescription, Date prescriptionDate,
			String billTo, String quantity, Long doctorId) {
		this.id = id;
		this.medication = medication;
		this.prescription = prescription;
		this.prescriptionDate = prescriptionDate;
		this.billTo = billTo;
		this.quantity = quantity;
		this.doctorId = doctorId;
	}
	
	
	
}
