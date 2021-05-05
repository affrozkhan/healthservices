package com.health.controller.api.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PATIENTS")
@Getter
@Setter
public class Patients extends AbstractColumnDetails{


	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="mobile_number")
	private String mobileNumber;
	
	@Column(name="address")
	private String address;
	
	@Column(name="blood_group")
	private String bloodGroup;
	
	@Column(name="height")
	private String height;
	
	@Column(name="weight")
	private String weight;
	
	
	@Id
	@JsonIgnore
	@Column(name="user_role_id")
	private Long userRoleId;
	 
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="USER_ROLES",
	        joinColumns = {@JoinColumn(name="id", referencedColumnName="user_role_id")},
	        inverseJoinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")}
	    )
	private Users user;
	

}
