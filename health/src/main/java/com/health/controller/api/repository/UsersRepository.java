package com.health.controller.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.health.controller.api.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long>, JpaSpecificationExecutor<Users>{

	
	
	@Query(value="select id from USERS where username=:userName and password=:password and active_status=1",nativeQuery=true)
	public Long verifyUser(@Param("userName")String userName, @Param("password")String password);


	
	
}
