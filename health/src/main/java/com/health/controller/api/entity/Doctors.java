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
@Table(name = "DOCTORS")
@Getter
@Setter
public class Doctors extends AbstractColumnDetails{

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@SequenceGenerator(name = "seq", sequenceName = "doctors_id_seq", allocationSize = 1)
	@Column(name="ID",nullable = false)
	private Long id;

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
	
	@JsonIgnore
	@Column(name="user_role_id")
	private Long userRoleId;
	 
	/*@NotFound(action=NotFoundAction.IGNORE)
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="USER_ROLES",
	        joinColumns = {@JoinColumn(name="id", referencedColumnName="user_role_id",insertable=false,updatable=false)},
	        inverseJoinColumns = {@JoinColumn(name="user_id", referencedColumnName="id",insertable=false,updatable=false)}
	    )
	private Users user;*/
	

}
