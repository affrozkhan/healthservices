package com.health.controller.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.health.controller.api.entity.Doctors;

public interface DoctorsRepository extends JpaRepository<Doctors, Long>, JpaSpecificationExecutor<Doctors>{

	
	
	@Query(value="select d from Doctors d where d.activeStatus=1")
	public List<Doctors> findAllActiveDoctors();

	
	
	
	
	
}
