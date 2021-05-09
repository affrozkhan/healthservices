package com.health.controller.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.controller.api.dataexchange.request.PatientAllergiesRequest;
import com.health.controller.api.dataexchange.request.PatientsDiagnosesRequest;
import com.health.controller.api.dataexchange.request.PatientsRequest;
import com.health.controller.api.dataexchange.response.PatientAllergiesResponse;
import com.health.controller.api.dataexchange.response.PatientsDiagnosesResponse;
import com.health.controller.api.dataexchange.response.PatientsListResponse;
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
	
	
	public List<PatientsListResponse> fetchPatientsList() {
		List<PatientsListResponse> list=new ArrayList<PatientsListResponse>();
		List<Object[]>patlist=repository.fetchPatientsList();
		if(patlist!=null && patlist.size()>0){
			for (Object[] obj : patlist) {
				list.add(new PatientsListResponse
						((Long)obj[0], obj[1].toString(), obj[2].toString(), obj[3].toString(),(Date) obj[4],
								obj[5].toString()));
			}
		}
		return list;
	}


	public PatientsResponse fetchPatienDetails(Long patientid) {
		Patients entity=super.findById(patientid);
		if(entity!=null && entity.getId()!=null){
			return new PatientsResponse(entity.getId(), entity.getFirstName(), entity.getLastName(),
					entity.getPatientStatus(), entity.getSex(), entity.getDateOfBirth(), entity.getBloodGroup(), entity.getHeight(),
					entity.getWeight(), entity.getPlaceOfBirth(), entity.getOccupation(), entity.getEmail(), 
					entity.getMobileNumber(), entity.getAddress(), entity.getGuardianName(),
					entity.getGuardianRelation(), entity.getGuardianRelation()
					,parsePatientsDiagnosesList(entity.getPatientDiagnosesList()),
					parsePatientAllergiesList(entity.getPatientAllergiesList()),entity.getActiveStatus());
		}
		return null;
	}
	
	private List<PatientsDiagnosesResponse>parsePatientsDiagnosesList(List<PatientDiagnoses>list){
		List<PatientsDiagnosesResponse> resposnelist=new ArrayList<>();
		if(list!=null && list.size()>0){
			for (PatientDiagnoses entity : list) {
				PatientsDiagnosesResponse res=
						new PatientsDiagnosesResponse(entity.getId(),entity.getDiagnosesDate(), entity.getDiagnosesType(),
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
						new PatientAllergiesResponse(entity.getId(),entity.getDescription(),entity.getActiveStatus());
				resposnelist.add(res);
			}
		}
		return resposnelist;
		
	}


	public Map<String, Object> saveOrUpdatePatientDetails(PatientsRequest req) {
		Map<String, Object>res=new HashMap<>();
		res.put("patientresponse", null);
		res.put("message", "Request Failed");
		if(req==null){
			res.put("message", "Request Failed");
		}
		else if(req.getFirstName()==null || "".equals(req.getFirstName()) ){
			res.put("message", "First Name required");
		}else{
			Patients patient=new Patients(req.getPatientid(), req.getFirstName(), req.getLastName()
					, req.getEmail(), req.getMobileNumber(),
					req.getAddress(), req.getBloodGroup(), req.getHeight(), req.getWeight()
					, req.getSex(), req.getDateOfBirth(), 
					req.getPlaceOfBirth(), req.getOccupation(), req.getPatientStatus()
					, req.getGuardianName(), 
					req.getGuardianRelation(), req.getGuardianMobile(),req.getActiveStatus(),req.getUserId());
			
			
			patient=parsePatientDiagnosesList(req.getPatientDiagnosesList(),patient);
			patient=parsePatientAllergiesList(req.getPatientAllergiesList(),patient);
			patient=super.save(patient);
			
			res.put("patientresponse", patient.getId());
			res.put("message", "Request Saved successfully");
		}
		return res;
	}


	private Patients parsePatientAllergiesList(List<PatientAllergiesRequest> patientAllergiesList, Patients patient) {
		List<PatientAllergies >list=new ArrayList<>();
		if(patientAllergiesList!=null && patientAllergiesList.size()>0){
			for (PatientAllergiesRequest req : patientAllergiesList) {
				list.add(new PatientAllergies(req.getId(), req.getDescription(), patient,req.getActiveStatus(),patient.getUpdatedBy()));
			}
		}
		patient.setPatientAllergiesList(list);
		return patient;
	}


	private Patients parsePatientDiagnosesList(List<PatientsDiagnosesRequest> patientDiagnosesList, Patients patient) {
		List<PatientDiagnoses>list=new ArrayList<>();
		if(patientDiagnosesList!=null && patientDiagnosesList.size()>0){
			for (PatientsDiagnosesRequest req : patientDiagnosesList) {
				list.add(new PatientDiagnoses(req.getId(), req.getDiagnosesDate(),
						req.getDiagnosesType(), req.getDescription(), req.getRemarks(), patient,req.getActiveStatus(),patient.getUpdatedBy()));
			}
		}
		patient.setPatientDiagnosesList(list);
		return patient;
	}


	/*public List<PatientsResponse> fetchAllPatientsList() {
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
*/
	


}
