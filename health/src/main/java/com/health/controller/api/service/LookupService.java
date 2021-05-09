package com.health.controller.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.health.controller.api.dataexchange.response.LookupResponse;
import com.health.controller.api.entity.Lookup;
import com.health.controller.api.repository.LookupRepository;

@Service
public class LookupService extends GenericService<Lookup,Long>{

	@Autowired
	private LookupRepository repository;


	@Autowired
	public LookupService(LookupRepository repository) {
		super(repository);
		this.repository = repository;
	}


	public List<LookupResponse> fetchLookup(Long lookupId) {
		List<Lookup>list=repository.fetchLookup(lookupId);
		List<LookupResponse>res=new ArrayList<LookupResponse>();
		if(list!=null && list.size()>0){
			for (Lookup lookup : list) {
				LookupResponse entity=new LookupResponse(lookup.getLookupValue(), lookup.getDescription());
				res.add(entity);
			}
		}
		return res;
	}




}
