package com.health.controller.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.health.controller.api.entity.Lookup;
import com.health.controller.api.entity.LookupCompositeKey;

public interface LookupRepository extends JpaRepository<Lookup, LookupCompositeKey>, JpaSpecificationExecutor<Lookup>{

	

	
	@Query(value="select d from Lookup d where d.lookupKey=:lookupKey and d.activeStatus=1")
	public List<Lookup> fetchLookup(@Param("lookupKey")String lookupKey);

	@Query(value="select d.description from Lookup d where d.lookupKey=:lookupKey and d.lookupValue=:lookupValue")
	public String fetchLookDescription(@Param("lookupKey")String lookupKey,@Param("lookupValue") String lookupValue);
	
}
