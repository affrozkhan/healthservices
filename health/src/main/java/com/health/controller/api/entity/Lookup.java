package com.health.controller.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "LOOKUP")
@Getter
@Setter
public class Lookup{

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@SequenceGenerator(name = "seq", sequenceName = "lookup_id_seq", allocationSize = 1)
	@Column(name="ID",nullable = false)
	private Long id;

	@Column(name="look_up_key")
	private String lookupKey;
	
	@Column(name="look_up_value")
	private Long lookupValue;

	@Column(name="description")
	private String description;
	
	
	
	
	@JsonIgnore
	@Column(name="active_status")
	private Long activeStatus;
	

}
