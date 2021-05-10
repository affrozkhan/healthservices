package com.health.controller.api.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class Users extends AbstractColumnDetails {

	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@SequenceGenerator(name = "seq", sequenceName = "users_id_seq", allocationSize = 1)
	@Column(name="ID",nullable = false)
	private Long id;

	@Column(name="username")
	private String username;

	@Column(name="password")
	private String password;

	@Column(name="last_login")
	private Date lastLogin;

	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="USER_ROLES",
	        joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id",insertable=false,updatable=false)},
	        inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id",insertable=false,updatable=false)}
	    )
	private List<Roles> roles;
	
	
}
