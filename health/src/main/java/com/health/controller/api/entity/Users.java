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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class Users extends AbstractColumnDetails {

	
	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@SequenceGenerator(name = "seq", sequenceName = "users_id_seq", allocationSize = 1)
	@Column(name="ID",nullable = false)
	private Long id;

	@Column(name="username")
	private String username;

	@JsonIgnore
	@Column(name="password")
	private String password;

	@JsonIgnore
	@Column(name="last_login")
	private Date lastLogin;

	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="USER_ROLES",
	        joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")},
	        inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")}
	    )
	private List<Roles> roles;
	
	
	@Transient
	private List<Menus>menus;
	
}