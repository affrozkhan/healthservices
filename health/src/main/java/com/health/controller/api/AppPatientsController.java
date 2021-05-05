package com.health.controller.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
@CrossOrigin(origins="*", allowCredentials="true", allowedHeaders ="*")
@RestController
@RequestMapping("/health/apppatientsui")
@Api(value="Patient's App Service Controller", description="Operations related to Patient's App Service Controller")
public class AppPatientsController {


	
	

}
