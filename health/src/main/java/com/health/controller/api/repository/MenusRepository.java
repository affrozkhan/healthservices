package com.health.controller.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.health.controller.api.entity.Menus;

public interface MenusRepository extends JpaRepository<Menus, Long>, JpaSpecificationExecutor<Menus>{

	

	
	@Query(value="select * from SYSTEM_MENU where parent_id=:pid and active_status=1",nativeQuery=true)
	public List<Menus> selectByPid(@Param("pid")Long pid);
	
	@Query(value="select * from SYSTEM_MENU where parent_id!=0 and active_status=1 ",nativeQuery=true)
	public List<Menus> selectAllNotBase();

	@Query(value="select count(*) from SYSTEM_MENU_ROLES where menu_id=:menuid and role_id=:roleid and active_status=1 ",nativeQuery=true)
	public int checkmenuaccess(@Param("menuid")Long menuid, @Param("roleid")Long roleid);
	
	
}
