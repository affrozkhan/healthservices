package com.health.controller.api.entity;

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
@Table(name = "SYSTEM_MENU")
@Getter
@Setter
public class Menus extends AbstractColumnDetails{

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@SequenceGenerator(name = "seq", sequenceName = "system_menu_id_seq", allocationSize = 1)
	@Column(name="ID",nullable = false)
	private Long id;

	@Column(name="name")
	private String name;

	@Column(name="action")
	private String action;

	@JsonIgnore
	@Column(name="parent_id")
	private Long parentid;

	@Transient
	private List<Menus> submenus;
	
	
	@JsonIgnore
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinTable(name="SYSTEM_MENU_ROLES",
	        joinColumns = {@JoinColumn(name="menu_id", referencedColumnName="id",insertable=false,updatable=false)},
	        inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id",insertable=false,updatable=false)}
	    )
	private List<Roles> roles;


}
