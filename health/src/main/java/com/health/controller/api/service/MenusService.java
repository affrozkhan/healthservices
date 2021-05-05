package com.health.controller.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.controller.api.entity.Menus;
import com.health.controller.api.repository.MenusRepository;

@Service
public class MenusService extends GenericService<Menus, Long>{

	@Autowired
	private MenusRepository repository;


	@Autowired
	public MenusService(MenusRepository repository) {
		super(repository);
		this.repository = repository;
	}


	public List<Menus> menusUnderMainMenu(Long i) {
		return repository.selectByPid(i);
	}


	public List<Menus> selectAllNotBase() {
		return repository.selectAllNotBase();
	}


	public Boolean checkmenuaccess(Long menuid, Long roleid) {
		return repository.checkmenuaccess(menuid,roleid)>0?true:false;
		
	}
	


}
