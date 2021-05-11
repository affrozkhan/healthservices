package com.health.controller.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.health.controller.api.entity.PatientAppointments;

public interface PatientAppointmentsRepository extends JpaRepository<PatientAppointments, Long>, JpaSpecificationExecutor<PatientAppointments>{

	
	
}
