package com.health.controller.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.health.controller.api.entity.Lookup;

public interface LookupRepository extends JpaRepository<Lookup, Long>, JpaSpecificationExecutor<Lookup>{

	

	
	@Query(value="select d from Lookup d where d.lookupKey=:lookupId and d.activeStatus=1")
	public List<Lookup> fetchLookup(@Param("lookupId")Long lookupId);
	
}
