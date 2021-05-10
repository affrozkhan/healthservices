package com.health.controller.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "LOOKUP")
@Getter
@Setter
@IdClass(LookupCompositeKey.class)
public class Lookup{


	@Id
	@Column(name="look_up_key")
	private String lookupKey;
	
	@Id
	@Column(name="look_up_value")
	private Long lookupValue;

	@Column(name="description")
	private String description;
	
	@Column(name="active_status")
	private Long activeStatus;
	

}
