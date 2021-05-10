package com.health.controller.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
@CrossOrigin(origins="*", allowCredentials="true", allowedHeaders ="*")
@RestController
@RequestMapping("/health/appsui")
@Api(value="App Service Controller", description="Operations related to App Service Controller")
public class AppController {


	
	

}
