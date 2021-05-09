package com.health.controller.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.config.Constants;
import com.health.controller.api.dataexchange.request.PatientAllergiesRequest;
import com.health.controller.api.dataexchange.request.PatientAppointmentsRequest;
import com.health.controller.api.dataexchange.request.PatientMedicationsRequest;
import com.health.controller.api.dataexchange.request.PatientsDiagnosesRequest;
import com.health.controller.api.dataexchange.request.PatientsRequest;
import com.health.controller.api.dataexchange.response.PatientAllergiesResponse;
import com.health.controller.api.dataexchange.response.PatientMedicationsResponse;
import com.health.controller.api.dataexchange.response.PatientsAppointmentsResponse;
import com.health.controller.api.dataexchange.response.PatientsDiagnosesResponse;
import com.health.controller.api.dataexchange.response.PatientsLazyListResponse;
import com.health.controller.api.dataexchange.response.PatientsListResponse;
import com.health.controller.api.dataexchange.response.PatientsResponse;
import com.health.controller.api.entity.PatientAllergies;
import com.health.controller.api.entity.PatientAppointments;
import com.health.controller.api.entity.PatientDiagnoses;
import com.health.controller.api.entity.PatientMedications;
import com.health.controller.api.entity.Patients;
import com.health.controller.api.repository.PatientsRepository;


@Service
public class PatientsService extends GenericService<Patients, Long>{

	@Autowired
	private PatientsRepository repository;

	@Autowired
	EntityManager entityManager;

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


	public PatientsLazyListResponse fetchPatientsListWithCriteria(Map<String,Object> filter) {
		PatientsLazyListResponse res=new PatientsLazyListResponse(); 
		List<Patients> data;
		
		int pageNumber=(int) filter.get("pageNumber");
		int pageSize=(int) filter.get("pageSize");
		String sortField= (String) filter.get("sortField")!=null  && !"".equals((String) filter.get("sortField"))?(String) filter.get("sortField"):"id";
		String sortOrder=(String) filter.get("sortOrder")!=null  && !"".equals((String) filter.get("sortOrder"))?(String) filter.get("sortOrder"):"DESC";
		
		
		Integer startRowNum = pageSize * (pageNumber - 1);
		String queryString = "select new com.health.controller.api.entity.Patients("
                + "d.id, d.firstName,d.lastName,d.sex,d.dateOfBirth,d.patientStatus)" + " from Patients d "
                + "WHERE d.activeStatus=1 ";
		
		
		queryString += " ORDER BY d."+sortField;
		queryString += " "+sortOrder;
		
		Query querySize = entityManager.createQuery(queryString.toString());
		Query queryCount = entityManager.createQuery(queryString.toString());
		
		data = (List<Patients>) querySize.setFirstResult(startRowNum)
				.setMaxResults(pageSize).getResultList();

		List<Patients> totalCount = (List<Patients>) queryCount.getResultList();

		if (data != null) {
			res.setRowCount((long)data.size());
		}

		if (totalCount != null) {
			res.setTotalCount((long)totalCount.size());
		}

		
		List<PatientsListResponse> list=new ArrayList<PatientsListResponse>();
		if(data!=null && data.size()>0){
			for (Patients obj : data) {
				list.add(new PatientsListResponse(obj.getId(), obj.getFirstName(), 
						obj.getLastName(), obj.getSex(), obj.getDateOfBirth(), obj.getPatientStatus()));
						
			}
		}
		res.setPatientList(list);
		return  res;
	}

	public PatientsResponse fetchPatienDetails(Long patientid) {
		Patients entity=super.findById(patientid);
		if(entity!=null && entity.getId()!=null){
			return new PatientsResponse(entity.getId(), entity.getFirstName(), entity.getLastName(),
					entity.getPatientStatus(), entity.getSex(), entity.getDateOfBirth(), entity.getBloodGroup(), entity.getHeight(),
					entity.getWeight(), entity.getPlaceOfBirth(), entity.getOccupation(), entity.getEmail(), 
					entity.getMobileNumber(), entity.getAddress(), entity.getGuardianName(),
					entity.getGuardianRelation(), entity.getGuardianMobile()
					,parsePatientsDiagnosesList(entity.getPatientDiagnosesList()),
					parsePatientAllergiesList(entity.getPatientAllergiesList())
					,parsePatientAppointmentsList(entity.getAppointmentsList())
					,parsePatientMedicationsList(entity.getPatientMedicationsList()));
		}
		return null;
	}

	private List<PatientMedicationsResponse> parsePatientMedicationsList(
			List<PatientMedications> list) {
		List<PatientMedicationsResponse> resposnelist=new ArrayList<>();
		if(list!=null && list.size()>0){
			for (PatientMedications req : list) {
				PatientMedicationsResponse res=
						new PatientMedicationsResponse(req.getId(), req.getMedication(), req.getPrescription(), req.getPrescriptionDate(), req.getBillTo(),
								req.getQuantity(), req.getDoctorId());
				resposnelist.add(res);

			}
		}
		return resposnelist;
	}


	private List<PatientsAppointmentsResponse> parsePatientAppointmentsList(List<PatientAppointments> list) {
		List<PatientsAppointmentsResponse> resposnelist=new ArrayList<>();
		if(list!=null && list.size()>0){
			for (PatientAppointments entity : list) {
				PatientsAppointmentsResponse res=
						new PatientsAppointmentsResponse(entity.getId(), entity.getStatus(), entity.getType(), 
								entity.getLocation(), entity.getNotes(), entity.getStartDate(), entity.getStartTime(),
								entity.getEndDate(), entity.getEndTime(), entity.getDoctorId());
				resposnelist.add(res);

			}
		}
		return resposnelist;
	}


	private List<PatientsDiagnosesResponse>parsePatientsDiagnosesList(List<PatientDiagnoses>list){
		List<PatientsDiagnosesResponse> resposnelist=new ArrayList<>();
		if(list!=null && list.size()>0){
			for (PatientDiagnoses entity : list) {
				PatientsDiagnosesResponse res=
						new PatientsDiagnosesResponse(entity.getId(),entity.getDiagnosesDate(), entity.getDiagnosesType(),
								entity.getDescription(), entity.getRemarks());
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
						new PatientAllergiesResponse(entity.getId(),entity.getDescription());
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
			Patients patient=		new Patients(req.getPatientid(), req.getFirstName(), req.getLastName()
					, req.getEmail(), req.getMobileNumber(),
					req.getAddress(), req.getBloodGroup(), req.getHeight(), req.getWeight()
					, req.getSex(), req.getDateOfBirth(), 
					req.getPlaceOfBirth(), req.getOccupation(), req.getPatientStatus()
					, req.getGuardianName(), 
					req.getGuardianRelation(), req.getGuardianMobile(),Constants.ACTIVE,req.getUserId());


			patient=parsePatientDiagnosesList(req.getPatientDiagnosesList(),patient);
			patient=parsePatientAllergiesList(req.getPatientAllergiesList(),patient);
			patient=parsePatientAppointmentsList(req.getPatientAppointmentsList(),patient);
			patient=parsePatientMedicationsList(req.getPatientMedicationsList(),patient);
			patient=super.save(patient);

			res.put("patientresponse", patient.getId());
			res.put("message", "Request Saved successfully");
		}
		return res;
	}


	private Patients parsePatientMedicationsList(List<PatientMedicationsRequest> patientMedicationsList,
			Patients patient) {
		List<PatientMedications >list=new ArrayList<>();
		if(patientMedicationsList!=null && patientMedicationsList.size()>0){
			for (PatientMedicationsRequest req : patientMedicationsList) {
				list.add(new PatientMedications(req.getId(), req.getMedication(), req.getPrescription(), req.getPrescriptionDate(), req.getBillTo(),
						req.getQuantity(), patient, req.getDoctorId(),Constants.ACTIVE,patient.getUpdatedBy()));

			}
		}
		patient.setPatientMedicationsList(list);
		return patient;
	}


	private Patients parsePatientAppointmentsList(List<PatientAppointmentsRequest> patientAppointmentsList,
			Patients patient) {
		List<PatientAppointments >list=new ArrayList<>();
		if(patientAppointmentsList!=null && patientAppointmentsList.size()>0){
			for (PatientAppointmentsRequest req : patientAppointmentsList) {
				list.add(new PatientAppointments
						(req.getId(), req.getAppointmentStatus(),
								req.getAppointmentType(), req.getLocation(), req.getNotes(),
								req.getStartDate(), req.getStartTime(), req.getEndDate(), req.getEndTime(),
								patient, req.getDoctorId(),Constants.ACTIVE,patient.getUpdatedBy()));
			}
		}
		patient.setAppointmentsList(list);
		return patient;
	}


	private Patients parsePatientAllergiesList(List<PatientAllergiesRequest> patientAllergiesList, Patients patient) {
		List<PatientAllergies >list=new ArrayList<>();
		if(patientAllergiesList!=null && patientAllergiesList.size()>0){
			for (PatientAllergiesRequest req : patientAllergiesList) {
				list.add(new PatientAllergies(req.getId(), req.getDescription(), patient,Constants.ACTIVE,patient.getUpdatedBy()));
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
						req.getDiagnosesType(), req.getDescription(), req.getRemarks(), patient,Constants.ACTIVE,patient.getUpdatedBy()));
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
