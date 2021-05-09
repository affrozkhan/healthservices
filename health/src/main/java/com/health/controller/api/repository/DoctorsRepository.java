package com.health.controller.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.health.controller.api.entity.Doctors;

public interface DoctorsRepository extends JpaRepository<Doctors, Long>, JpaSpecificationExecutor<Doctors>{

	
	
	/*@Query(value="select d from Doctors d where d.user.username=:username and d.user.activeStatus=1")
	public Doctors fetchdoctorbyusername(@Param("username")String username);

	*/
	
	
	
	
}
