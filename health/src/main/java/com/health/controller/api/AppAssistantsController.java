package com.health.controller.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.controller.api.dataexchange.response.AssistantAppointmentResponse;
import com.health.controller.api.dataexchange.response.PatientsAppointmentResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/health/appassistantsui")
@Api(value="Assistant's App Service Controller", description="Operations related to Assistant's App Service Controller")
public class AppAssistantsController {



	@ApiOperation(value = "Get all appointments by Assistantid and Date")
	@GetMapping("/getallappointments/{assistantid}/{date}")
	public List<AssistantAppointmentResponse> myAppointments(
			@ApiParam(value = "Get all appointments by userid", required = true)
			@PathVariable(value = "assistantid") Long assistantid,
			@PathVariable(value = "date") Date date) {
		List<AssistantAppointmentResponse> list=new ArrayList<>();
		list.add(new AssistantAppointmentResponse("User1", new Date(), "Dr.Amar", "7337731482", "KLE Hospital"));
		list.add(new AssistantAppointmentResponse("User2", new Date(), "Dr.Amar", "3213232321", "DSA Hospital"));
		list.add(new AssistantAppointmentResponse("User3", new Date(), "Dr.Amar", "4421232321", "FFF Hospital"));
		return list;

	}

	
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
