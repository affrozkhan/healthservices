package com.health.controller.api.dataexchange.request;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.health.config.Constants;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientMedicationsRequest {
	

	
	
	private Long id;
	private String medication;
	private String prescription;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
	private Date prescriptionDate;
	
	private String billTo;
	private String quantity;
	private Long doctorId;
	
	
	
	
	
	
	

	
	
}
