package com.health.controller.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.controller.api.entity.Users;
import com.health.controller.api.repository.UsersRepository;

@Service
public class UsersService extends GenericService<Users, Long>{

	@Autowired
	private UsersRepository repository;

	
	@Autowired
	public UsersService(UsersRepository repository) {
		super(repository);
		this.repository = repository;
	}


	public Long verifyUser(String userName, String password) {
		return repository.verifyUser(userName,password);
		
	}
	
	

}
