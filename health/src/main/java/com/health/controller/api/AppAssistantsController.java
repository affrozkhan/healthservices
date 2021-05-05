package com.health.controller.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
@CrossOrigin(origins="*", allowCredentials="true", allowedHeaders ="*")
@RestController
@RequestMapping("/health/appassistantsui")
@Api(value="Assistant's App Service Controller", description="Operations related to Assistant's App Service Controller")
public class AppAssistantsController {




}
