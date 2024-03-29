package com.health.controller.api.service;

import java.util.ArrayList;
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
import com.health.controller.api.dataexchange.request.PatientTestRequest;
import com.health.controller.api.dataexchange.request.PatientsDiagnosesRequest;
import com.health.controller.api.dataexchange.request.PatientsRequest;
import com.health.controller.api.dataexchange.response.PatientAllergiesResponse;
import com.health.controller.api.dataexchange.response.PatientMedicationsResponse;
import com.health.controller.api.dataexchange.response.PatientTestResponse;
import com.health.controller.api.dataexchange.response.PatientsAppointmentsResponse;
import com.health.controller.api.dataexchange.response.PatientsDiagnosesResponse;
import com.health.controller.api.dataexchange.response.PatientsLazyListResponse;
import com.health.controller.api.dataexchange.response.PatientsListResponse;
import com.health.controller.api.dataexchange.response.PatientsResponse;
import com.health.controller.api.entity.PatientAllergies;
import com.health.controller.api.entity.PatientAppointments;
import com.health.controller.api.entity.PatientDiagnoses;
import com.health.controller.api.entity.PatientMedications;
import com.health.controller.api.entity.PatientTests;
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



	@SuppressWarnings("unchecked")
	public Map<String , Object> fetchPatientsListWithCriteria(Map<String,Object> filter) {
		Map<String , Object>response=new HashMap<>();
		if(filter==null || filter.isEmpty()){
			response.put(Constants.ERROR_KEY, "Filter Map Cannot be empty");
		}
		else if(!filter.containsKey("pageNumber") || filter.get("pageNumber")==null){
			response.put(Constants.ERROR_KEY, "pageNumber Key is missing in Filter Map");
		}
		else if(!filter.containsKey("pageSize") || filter.get("pageSize")==null){
			response.put(Constants.ERROR_KEY, "pageSize Key is missing in Filter Map");
		}
		else if(!filter.containsKey("sortField") || filter.get("sortField")==null || "".equals((String) filter.get("sortField"))){
			response.put(Constants.ERROR_KEY, "sortField Key is missing in Filter Map");
		}
		else if(!filter.containsKey("sortOrder") || filter.get("sortOrder")==null || "".equals((String) filter.get("sortOrder"))){
			response.put(Constants.ERROR_KEY, "sortOrder Key is missing in Filter Map");
		}
		else{
			PatientsLazyListResponse res=new PatientsLazyListResponse(); 
			List<Patients> data;

			int pageNumber=(int) filter.get("pageNumber");
			int pageSize=(int) filter.get("pageSize");
			String sortField= (String) filter.get("sortField")!=null  && !"".equals((String) filter.get("sortField"))?(String) filter.get("sortField"):"id";
			String sortOrder=(String) filter.get("sortOrder")!=null  && !"".equals((String) filter.get("sortOrder"))?(String) filter.get("sortOrder"):"DESC";

			filter.remove("pageNumber");
			filter.remove("pageSize");
			filter.remove("sortField");
			filter.remove("sortOrder");

			Integer startRowNum = pageSize * (pageNumber - 1);
			String queryString = "select new com.health.controller.api.entity.Patients("
					+ "d.id, d.firstName,d.lastName,d.sex,d.dateOfBirth,d.patientStatus)" + " from Patients d "
					+ "WHERE d.activeStatus=1 ";

			if(filter.size()>0 && !filter.isEmpty()){
				for (Map.Entry<String, Object>  entry : filter.entrySet()){
					if(entry.getValue() instanceof String){
						queryString += " and d."+entry.getKey()+" like '%"+entry.getValue()+"%'";
					}
					else if(entry.getValue() instanceof Number){
						queryString += " and d."+entry.getKey()+" = "+entry.getValue();
					}

				}
			}



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
			response.put(Constants.SUCCESS_KEY, res);
		}
		return response;


	}


	//***************Patient data from Database to API Response*******************************************START
	public PatientsResponse fetchPatienDetails(Long patientid) {
		Patients entity=super.findById(patientid);
		if(entity!=null && entity.getId()!=null){
			return new PatientsResponse(entity.getId(), entity.getFirstName(), entity.getLastName(),
					entity.getPatientStatus(), entity.getSex(), entity.getDateOfBirth(), entity.getBloodGroup(), entity.getHeight(),
					entity.getWeight(), entity.getPlaceOfBirth(), entity.getOccupation(), entity.getEmail(), 
					entity.getMobileNumber(), entity.getAddress(), entity.getGuardianName(),
					entity.getGuardianRelation(), entity.getGuardianMobile()
					,parsePatientsDiagnosesListForResponse(entity.getPatientDiagnosesList()),
					parsePatientAllergiesListForResponse(entity.getPatientAllergiesList())
					,parsePatientAppointmentsListForResponse(entity.getAppointmentsList()));
		}
		return null;
	}
	private List<PatientsDiagnosesResponse>parsePatientsDiagnosesListForResponse(List<PatientDiagnoses>list){
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


	private List<PatientAllergiesResponse>parsePatientAllergiesListForResponse(List<PatientAllergies>list){
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

	private List<PatientsAppointmentsResponse> parsePatientAppointmentsListForResponse(List<PatientAppointments> list) {
		List<PatientsAppointmentsResponse> resposnelist=new ArrayList<>();
		if(list!=null && list.size()>0){
			for (PatientAppointments entity : list) {
				PatientsAppointmentsResponse res=
						new PatientsAppointmentsResponse(entity.getId(), entity.getStatus(), entity.getType(), 
								entity.getLocation(), entity.getNotes(), 
								entity.getStartDate(),
								Constants.convertDateToLocalTime(entity.getStartTime()),
								entity.getEndDate(), 
								Constants.convertDateToLocalTime(entity.getEndTime()), entity.getDoctorId(),
								parsePatientMedicationsListForResponse(entity.getPatientMedicationsList()),
								parsePatientTestListForResponse(entity.getPatientTestsList()));
				resposnelist.add(res);

			}
		}
		return resposnelist;
	}

	private List<PatientMedicationsResponse> parsePatientMedicationsListForResponse(
			List<PatientMedications> list) {
		List<PatientMedicationsResponse> resposnelist=new ArrayList<>();
		if(list!=null && list.size()>0){
			for (PatientMedications req : list) {
				PatientMedicationsResponse res=
						new PatientMedicationsResponse(req.getId(), req.getMedication(), req.getPrescription(), req.getPrescriptionDate(), req.getBillTo(),
								req.getQuantity());
				resposnelist.add(res);

			}
		}
		return resposnelist;
	}

	private List<PatientTestResponse> parsePatientTestListForResponse(List<PatientTests> list) {
		List<PatientTestResponse> resposnelist=new ArrayList<>();
		if(list!=null && list.size()>0){
			for (PatientTests req : list) {
				PatientTestResponse res=
						new PatientTestResponse(req.getId(), req.getTestType(), req.getTestDetails(), 
								req.getNotes(), req.getStatus(), req.getResult(),req.getTestDate());
				resposnelist.add(res);

			}
		}
		return resposnelist;
	}


	//***************Patient data from Database to API Response*******************************************END










	//***************Patient data saving to Database******************************************************START


	public Map<String, Object> saveOrUpdatePatientDetails(PatientsRequest req) {
		Map<String, Object>res=new HashMap<>();
		if(req==null){
			res.put(Constants.ERROR_KEY, "Request Failed");
		}
		else if(req.getFirstName()==null || "".equals(req.getFirstName()) ){
			res.put(Constants.ERROR_KEY, "First Name required");
		}else{
			Patients patient=		new Patients(req.getPatientid(), req.getFirstName(), req.getLastName()
					, req.getEmail(), req.getMobileNumber(),
					req.getAddress(), req.getBloodGroup(), req.getHeight(), req.getWeight()
					, req.getSex(), req.getDateOfBirth(), 
					req.getPlaceOfBirth(), req.getOccupation(), req.getPatientStatus()
					, req.getGuardianName(), 
					req.getGuardianRelation(), req.getGuardianMobile(),Constants.ACTIVE,req.getUserId());


			patient=parsePatientDiagnosesListForDB(req.getPatientDiagnosesList(),patient);
			patient=parsePatientAllergiesListForDB(req.getPatientAllergiesList(),patient);
			patient=parsePatientAppointmentsListForDB(req.getPatientAppointmentsList(),patient);
			patient=super.save(patient);

			res.put(Constants.SUCCESS_KEY, "Request Saved successfully with  ID:"+patient.getId());
		}
		return res;
	}


	private Patients parsePatientAllergiesListForDB(List<PatientAllergiesRequest> patientAllergiesList, Patients patient) {
		List<PatientAllergies >list=new ArrayList<>();
		if(patientAllergiesList!=null && patientAllergiesList.size()>0){
			for (PatientAllergiesRequest req : patientAllergiesList) {
				list.add(new PatientAllergies(req.getId(), req.getDescription(), patient,Constants.ACTIVE,patient.getUpdatedBy()));
			}
		}
		patient.setPatientAllergiesList(list);
		return patient;
	}


	private Patients parsePatientDiagnosesListForDB(List<PatientsDiagnosesRequest> patientDiagnosesList, Patients patient) {
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

	private Patients parsePatientAppointmentsListForDB(List<PatientAppointmentsRequest> patientAppointmentsList,
			Patients patient) {
		List<PatientAppointments >list=new ArrayList<>();
		if(patientAppointmentsList!=null && patientAppointmentsList.size()>0){
			for (PatientAppointmentsRequest req : patientAppointmentsList) {
				PatientAppointments en= new PatientAppointments
						(req.getId(), req.getAppointmentStatus(),
								req.getAppointmentType(), req.getLocation(), req.getNotes(),
								req.getStartDate(),
								Constants.convertLocalTimeToDate(req.getStartTime()),
								req.getEndDate(), 
								Constants.convertLocalTimeToDate(req.getEndTime()),
								patient, req.getDoctorId(),Constants.ACTIVE,patient.getUpdatedBy());

				en=parsePatientTestListForDB(req.getPatientTestsList(),en);
				en=parsePatientMedicationsListForDB(req.getPatientMedicationsList(),en);
				list.add(en);

			}
		}
		patient.setAppointmentsList(list);
		return patient;
	}



	private PatientAppointments parsePatientMedicationsListForDB(List<PatientMedicationsRequest> patientMedicationsList,
			PatientAppointments patient) {
		List<PatientMedications >list=new ArrayList<>();
		if(patientMedicationsList!=null && patientMedicationsList.size()>0){
			for (PatientMedicationsRequest req : patientMedicationsList) {
				list.add(new PatientMedications(req.getId(), req.getMedication(), req.getPrescription(), req.getPrescriptionDate(), req.getBillTo(),
						req.getQuantity(), patient,Constants.ACTIVE,patient.getUpdatedBy()));

			}
		}
		patient.setPatientMedicationsList(list);
		return patient;
	}

	private PatientAppointments parsePatientTestListForDB(List<PatientTestRequest> patientTestsList,
			PatientAppointments patient) {
		List<PatientTests >list=new ArrayList<>();
		if(patientTestsList!=null && patientTestsList.size()>0){
			for (PatientTestRequest req : patientTestsList) {
				list.add(new PatientTests(req.getId(), req.getTestType(), req.getTestDetails(),
						req.getNotes(), req.getStatus(), req.getResult(), req.getTestDate(),
						patient,Constants.ACTIVE,patient.getUpdatedBy()));

			}
		}
		patient.setPatientTestsList(list);
		return patient;
	}




	//***************Patient data saving to Database******************************************************END



}
