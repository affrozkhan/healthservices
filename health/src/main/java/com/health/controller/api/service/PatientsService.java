package com.health.controller.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.controller.api.dataexchange.response.PatientAllergiesResponse;
import com.health.controller.api.dataexchange.response.PatientsDiagnosesResponse;
import com.health.controller.api.dataexchange.response.PatientsResponse;
import com.health.controller.api.entity.PatientAllergies;
import com.health.controller.api.entity.PatientDiagnoses;
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
				PatientsResponse res=new PatientsResponse(entity.getUser().getUsername(), entity.getFirstName(), entity.getLastName(),
						entity.getPatientStatus(), entity.getSex(), entity.getDateOfBirth(), entity.getBloodGroup(), entity.getHeight(),
						entity.getWeight(), entity.getPlaceOfBirth(), entity.getOccupation(), entity.getEmail(), 
						entity.getMobileNumber(), entity.getAddress(), entity.getGuardianName(), entity.getGuardianRelation(), entity.getGuardianRelation()
						,parsePatientsDiagnosesList(entity.getPatientDiagnosesList()),
						parsePatientAllergiesList(entity.getPatientAllergiesList()));
				list.add(res);
			}
		}
		return list;
	}


	public PatientsResponse fetchPatientsbyusername(String username) {
		Patients entity=repository.fetchPatientsbyusername(username);
		if(entity!=null && entity.getUserRoleId()!=null){
			return new PatientsResponse(entity.getUser().getUsername(), entity.getFirstName(), entity.getLastName(),
					entity.getPatientStatus(), entity.getSex(), entity.getDateOfBirth(), entity.getBloodGroup(), entity.getHeight(),
					entity.getWeight(), entity.getPlaceOfBirth(), entity.getOccupation(), entity.getEmail(), 
					entity.getMobileNumber(), entity.getAddress(), entity.getGuardianName(),
					entity.getGuardianRelation(), entity.getGuardianRelation()
					,parsePatientsDiagnosesList(entity.getPatientDiagnosesList()),
					parsePatientAllergiesList(entity.getPatientAllergiesList()));
		}
		return null;
	}

	private List<PatientsDiagnosesResponse>parsePatientsDiagnosesList(List<PatientDiagnoses>list){
		List<PatientsDiagnosesResponse> resposnelist=new ArrayList<>();
		if(list!=null && list.size()>0){
			for (PatientDiagnoses entity : list) {
				PatientsDiagnosesResponse res=
						new PatientsDiagnosesResponse(entity.getDiagnosesDate(), entity.getDiagnosesType(),
								entity.getDescription(), entity.getRemarks(), entity.getActiveStatus());
				resposnelist.add(res);
			}
		}
		return resposnelist;
		
	}
	
	
	private List<PatientAllergiesResponse>parsePatientAllergiesList(List<PatientAllergies>list){
		List<PatientAllergiesResponse> resposnelist=new ArrayList<>();
		if(list!=null && list.size()>0){
			for (PatientAllergies entity : list) {
				PatientAllergiesResponse res=
						new PatientAllergiesResponse(entity.getDescription());
				resposnelist.add(res);
			}
		}
		return resposnelist;
		
	}


}
