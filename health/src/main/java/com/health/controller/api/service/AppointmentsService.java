package com.health.controller.api.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.controller.api.dataexchange.response.AppointmentsResponse;
import com.health.controller.api.entity.Appointments;
import com.health.controller.api.repository.AppointmentsRepository;

@Service
public class AppointmentsService extends GenericService<Appointments, Long>{

	@Autowired
	private AppointmentsRepository repository;

	
	@Autowired
	public AppointmentsService(AppointmentsRepository repository) {
		super(repository);
		this.repository = repository;
	}


	/*public List<AppointmentsResponse> fetchAllAppointments(Long docid,Long patid) {
		List<AppointmentsResponse> list=new ArrayList<AppointmentsResponse>();
		List<Appointments>appList=null;
		if(docid!=null){
			appList=repository.fetchappointmentsByDoctor(docid);
		}else{
			appList=repository.fetchappointmentsByPatient(patid);
		}
		if(appList!=null && appList.size()>0){
			for (Appointments entity : appList) {
				SimpleDateFormat datesdf = new SimpleDateFormat("dd/MM/yyyy");
				SimpleDateFormat timesdf = new SimpleDateFormat("HH:mm");
				AppointmentsResponse res=new AppointmentsResponse(entity.getStatus().toString(), datesdf.format(entity.getDate()),
						timesdf.format(entity.getTime()), entity.getPatient().getFirstName(), entity.getDoctors().getFirstName());
				list.add(res);
			}
		}
		return list;
	}
*/

}
