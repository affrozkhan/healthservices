package com.health;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
@ComponentScan({"com.health"})
@RequestMapping("/health")
public class WebserviceapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebserviceapiApplication.class, args);
	}

	
	
	
	
	
	
}
