package com.health.controller.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.health.controller.api.dataexchange.request.LoginRequest;
import com.health.controller.api.dataexchange.response.DoctorResponse;
import com.health.controller.api.dataexchange.response.MessageResponse;
import com.health.controller.api.dataexchange.response.PatientsResponse;
import com.health.controller.api.dataexchange.response.UserResponse;
import com.health.controller.api.service.DoctorsService;
import com.health.controller.api.service.MenusService;
import com.health.controller.api.service.PatientsService;
import com.health.controller.api.service.UsersService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;


@CrossOrigin(origins="*", allowCredentials="true", allowedHeaders ="*")
@Getter
@Setter
@RestController
@RequestMapping("/health/webui")
@Api(value="Web Service Controller", description="Operations related to Web Service Controller")
public class WebController {


	@Autowired
	private UsersService userservice;


	@Autowired
	private MenusService menusService;

	@Autowired
	private DoctorsService doctorsService;
	
	@Autowired
	private PatientsService patientsService;

	@ApiOperation(value = "Verify user credentials to login")
	@PostMapping("/verifyuser")
	public ResponseEntity<Object> verifyAndGetUserDetails(
			@ApiParam(value = "Get all appointments by userid", required = true)
			@Valid @RequestBody LoginRequest req) {
		if(req!=null && req.getUserName()!=null && req.getPassword()!=null){
			Long userid=userservice.verifyUser(req.getUserName(),req.getPassword());
			if(userid!=null && userid!=0L){
				UserResponse user=userservice.fetchUserdetails(userid,menusService);
				
				return new ResponseEntity<>(user, HttpStatus.OK);

			}else{
				return new ResponseEntity<>(new MessageResponse("Please verify Username and Password", "HCE-100"), HttpStatus.UNAUTHORIZED);	
			}

		}else{
			return new ResponseEntity<>(new MessageResponse("Please verify Username and Password", "HCE-100"), HttpStatus.UNAUTHORIZED);
		}
	}

	
	
	@ApiOperation(value = "Fetch List of Doctors")
	@GetMapping("/fetchalldocotrs")
	public ResponseEntity<Object> fetchalldocotrs() {
		List<DoctorResponse>list=doctorsService.fetchAllDoctorList();
		if(list!=null && list.size()>0){
			return new ResponseEntity<>(list, HttpStatus.OK);

		}else{
			return new ResponseEntity<>(new MessageResponse("No data Found", "HCE-100"), HttpStatus.OK);
		}
	}
	
	
	@ApiOperation(value = "Fetch Doctor details by Username")
	@GetMapping("/fetchdoctorbyusername/{username}")
	public ResponseEntity<Object> fetchdoctorbyusername(
			@ApiParam(value = "Fetch Doctor details by Username", required = true)
			@PathVariable(value = "username") String username) {
		DoctorResponse doc=doctorsService.fetchdoctorbyusername(username);
		if(doc!=null){
			return new ResponseEntity<>(doc, HttpStatus.OK);

		}else{
			return new ResponseEntity<>(new MessageResponse("No data Found", "HCE-100"), HttpStatus.OK);
		}
	}

	@ApiOperation(value = "Fetch List of Patient")
	@GetMapping("/fetchallpatients")
	public ResponseEntity<Object> fetchallPatients() {
		List<PatientsResponse>list=patientsService.fetchAllPatientsList();
		if(list!=null && list.size()>0){
			return new ResponseEntity<>(list, HttpStatus.OK);

		}else{
			return new ResponseEntity<>(new MessageResponse("No data Found", "HCE-100"), HttpStatus.OK);
		}
	}
	
	
	@ApiOperation(value = "Fetch Patient details by Username")
	@GetMapping("/fetchpatientbyusername/{username}")
	public ResponseEntity<Object> fetchpatientbyusername(
			@ApiParam(value = "Fetch Patient details by Username", required = true)
			@PathVariable(value = "username") String username) {
		PatientsResponse doc=patientsService.fetchPatientsbyusername(username);
		if(doc!=null){
			return new ResponseEntity<>(doc, HttpStatus.OK);

		}else{
			return new ResponseEntity<>(new MessageResponse("No data Found", "HCE-100"), HttpStatus.OK);
		}
	}

}
