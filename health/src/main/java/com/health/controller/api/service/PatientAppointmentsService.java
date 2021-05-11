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
import com.health.controller.api.dataexchange.response.PatientsAppointmentLazyListResponse;
import com.health.controller.api.dataexchange.response.PatientsAppointmentListResponse;
import com.health.controller.api.entity.PatientAppointments;
import com.health.controller.api.repository.PatientAppointmentsRepository;


@Service
public class PatientAppointmentsService extends GenericService<PatientAppointments, Long>{

	@Autowired
	private PatientAppointmentsRepository repository;

	@Autowired
	EntityManager entityManager;

	@Autowired
	public PatientAppointmentsService(PatientAppointmentsRepository repository) {
		super(repository);
		this.repository = repository;
	}



	@SuppressWarnings("unchecked")
	public Map<String , Object> fetchAppointmentList(Map<String,Object> filter) {
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
			PatientsAppointmentLazyListResponse res=new PatientsAppointmentLazyListResponse(); 
			List<PatientAppointments> data;
			
			int pageNumber=(int) filter.get("pageNumber");
			int pageSize=(int) filter.get("pageSize");
			String sortField= (String) filter.get("sortField")!=null  && !"".equals((String) filter.get("sortField"))?(String) filter.get("sortField"):"id";
			String sortOrder=(String) filter.get("sortOrder")!=null  && !"".equals((String) filter.get("sortOrder"))?(String) filter.get("sortOrder"):"DESC";
			
			filter.remove("pageNumber");
			filter.remove("pageSize");
			filter.remove("sortField");
			filter.remove("sortOrder");
			
			Integer startRowNum = pageSize * (pageNumber - 1);
		
			String queryString = "select new com.health.controller.api.entity.PatientAppointments("
	                + "d.id, d.status,d.type,d.location,d.startDate,d.startTime,d.endDate,d.endTime,d.patients,d.doctorId)" + " from PatientAppointments d "
	                + "WHERE d.activeStatus=1 ";
			
			if(filter.size()>0 && !filter.isEmpty()){
				 for (Map.Entry<String, Object>  entry : filter.entrySet()){
					 if(entry.getKey().contains("startDate")){
						

						 queryString += " and cast(d.startDate as date) BETWEEN TO_DATE('"+(String) entry.getValue()+"', '"+Constants.DATE_FORMAT+"') AND  TO_DATE('"+(String)filter.get("endDate")+"', '"+Constants.DATE_FORMAT+"') ";

					 }
					 else if(entry.getKey().contains("startTime")){
							queryString += " and cast(d.startTime as time) BETWEEN '"+(String) entry.getValue()+"' AND  '"+(String)filter.get("endTime")+"' ";
					 }
					 else if(entry.getValue() instanceof String && !entry.getKey().contains("endDate") && !entry.getKey().contains("endTime")){
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
			
			data = (List<PatientAppointments>) querySize.setFirstResult(startRowNum)
					.setMaxResults(pageSize).getResultList();

			List<PatientAppointments> totalCount = (List<PatientAppointments>) queryCount.getResultList();

			if (data != null) {
				res.setRowCount((long)data.size());
			}

			if (totalCount != null) {
				res.setTotalCount((long)totalCount.size());
			}

			
			List<PatientsAppointmentListResponse> list=new ArrayList<PatientsAppointmentListResponse>();
			if(data!=null && data.size()>0){
				for (PatientAppointments obj : data) {
					list.add(new PatientsAppointmentListResponse(obj.getId(), obj.getStatus(), obj.getType(),
							obj.getLocation(), obj.getStartDate(), Constants.convertDateToLocalTime(obj.getStartTime()), obj.getEndDate(), Constants.convertDateToLocalTime(obj.getEndTime()),
							obj.getPatients().getFirstName()+" "+obj.getPatients().getLastName(), obj.getDoctorId()));
							
				}
			}
			res.setAppointmentList(list);
			response.put(Constants.SUCCESS_KEY, res);
		}
		return response;
		
		
	}

	

}
