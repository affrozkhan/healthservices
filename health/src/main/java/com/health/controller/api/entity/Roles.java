package com.health.controller.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ROLES")
@Getter
@Setter
public class Roles extends AbstractColumnDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@SequenceGenerator(name = "seq", sequenceName = "roles_id_seq", allocationSize = 1)
	@Column(name="ID",nullable = false)
	private Long id;

	@Column(name="name")
	private String name;


	@Column(name="description")
	private String description;

	
}
