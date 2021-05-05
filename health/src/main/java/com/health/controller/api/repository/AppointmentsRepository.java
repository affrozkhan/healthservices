package com.health.controller.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.health.controller.api.entity.Appointments;

public interface AppointmentsRepository extends JpaRepository<Appointments, Long>, JpaSpecificationExecutor<Appointments>{

	
	
	@Query(value="select * from APPOINTMENT where doctor_id=:docid and active_status=1",nativeQuery=true)
	public List<Appointments> fetchappointmentsByDoctor(@Param("docid")Long docid);

	
	@Query(value="select * from APPOINTMENT where patient_id=:patid and active_status=1",nativeQuery=true)
	public List<Appointments> fetchappointmentsByPatient(@Param("patid")Long patid);

	
	
	
}
