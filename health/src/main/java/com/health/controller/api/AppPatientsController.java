package com.health.controller.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.controller.api.dataexchange.response.PatientsAppointmentResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/health/apppatientsui")
@Api(value="Patient's App Service Controller", description="Operations related to Patient's App Service Controller")
public class AppPatientsController {


	
	@ApiOperation(value = "Get all appointments by userid")
	@GetMapping("/myappointments/{userid}")
	public List<PatientsAppointmentResponse> myAppointments(
			@ApiParam(value = "Get all appointments by userid", required = true)
			@PathVariable(value = "userid") Long userid) {
		if(userid==1L){
			 List<PatientsAppointmentResponse> list=new ArrayList<>();
			 list.add(new PatientsAppointmentResponse( new Date(), "Dr.Amar", "7337731482","KLE Hospital"));
			 list.add(new PatientsAppointmentResponse( new Date(), "Dr.Akbar", "73920347292","SRS Hospital"));
			 list.add(new PatientsAppointmentResponse( new Date(), "Dr.Anthony", "32321323213","LLL Hospital"));
			 return list;
		}
		return null;
	}


}
