package com.health.controller.api.dataexchange.response;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.health.config.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientMedicationsResponse {
	
	private Long id;
	private String medication;
	private String prescription;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
	private Date prescriptionDate;
	
	private String billTo;
	private String quantity;
	
	public PatientMedicationsResponse(Long id, String medication, String prescription, Date prescriptionDate,
			String billTo, String quantity) {
		this.id = id;
		this.medication = medication;
		this.prescription = prescription;
		this.prescriptionDate = prescriptionDate;
		this.billTo = billTo;
		this.quantity = quantity;
	}
	
	
	
}
