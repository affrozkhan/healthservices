package com.health.controller.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.controller.api.dataexchange.response.PatientsResponse;
import com.health.controller.api.entity.Patients;
import com.health.controller.api.repository.PatientsRepository;

@Service
public class PatientsService extends GenericService<Patients, Long>{

	@Autowired
	private PatientsRepository repository;

	
	@Autowired
	public PatientsService(PatientsRepository repository) {
		super(repository);
		this.repository = repository;
	}


	public List<PatientsResponse> fetchAllPatientsList() {
		List<PatientsResponse> list=new ArrayList<PatientsResponse>();
		List<Patients>patlist=super.findAll();
		if(patlist!=null && patlist.size()>0){
			for (Patients entity : patlist) {
				PatientsResponse res=new PatientsResponse(entity.getFirstName(), entity.getLastName()
						, entity.getEmail(), entity.getMobileNumber(), entity.getAddress(),entity.getBloodGroup()
						,entity.getHeight(),entity.getWeight(), entity.getUser().getUsername());
				list.add(res);
			}
		}
		return list;
	}


	public PatientsResponse fetchPatientsbyusername(String username) {
		Patients entity=repository.fetchPatientsbyusername(username);
		if(entity!=null && entity.getUserRoleId()!=null){
			return new PatientsResponse(entity.getFirstName(), entity.getLastName()
					, entity.getEmail(), entity.getMobileNumber(), entity.getAddress(),entity.getBloodGroup()
					,entity.getHeight(),entity.getWeight(), entity.getUser().getUsername());
		}
		return null;
	}

	

}
