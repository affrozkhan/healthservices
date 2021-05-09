package com.health.controller.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.controller.api.dataexchange.response.DoctorResponse;
import com.health.controller.api.entity.Doctors;
import com.health.controller.api.repository.DoctorsRepository;

@Service
public class DoctorsService extends GenericService<Doctors, Long>{

	@Autowired
	private DoctorsRepository repository;


	@Autowired
	public DoctorsService(DoctorsRepository repository) {
		super(repository);
		this.repository = repository;
	}


	public List<DoctorResponse> fetchDoctors() {
		List<DoctorResponse> list=new ArrayList<DoctorResponse>();
		List<Doctors>doclist=repository.findAllActiveDoctors();
		if(doclist!=null && doclist.size()>0){
			for (Doctors doctors : doclist) {
				DoctorResponse res=new DoctorResponse(doctors.getId(), doctors.getFirstName(),
						doctors.getLastName(), doctors.getEmail(), doctors.getMobileNumber());

				list.add(res);
			}
		}
		return list;
	}


	/*public List<DoctorResponse> fetchAllDoctorList() {

	}


	public DoctorResponse fetchdoctorbyusername(String username) {
		Doctors doctors=repository.fetchdoctorbyusername(username);
		if(doctors!=null && doctors.getUserRoleId()!=null){
			return new DoctorResponse(doctors.getFirstName(), doctors.getLastName()
						, doctors.getEmail(), doctors.getMobileNumber(), doctors.getAddress(), 
						doctors.getUser().getUsername());
		}
		return null;
	}
	 */


}
