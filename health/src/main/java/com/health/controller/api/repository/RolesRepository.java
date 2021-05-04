package com.health.controller.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.health.controller.api.entity.Roles;

public interface RolesRepository extends JpaRepository<Roles, Long>, JpaSpecificationExecutor<Roles>{

	
	
	
	
	
}
