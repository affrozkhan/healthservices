package com.health.controller.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.health.controller.api.entity.Patients;

public interface PatientsRepository extends JpaRepository<Patients, Long>, JpaSpecificationExecutor<Patients>{

	
	
	@Query(value="select d from Patients d where d.user.username=:username and d.user.activeStatus=1")
	public Patients fetchPatientsbyusername(@Param("username")String username);

	
	
	
	
	
}