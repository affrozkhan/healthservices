package com.health.controller.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER_ROLES")
@Getter
@Setter
public class UserRoles extends AbstractColumnDetails {

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@SequenceGenerator(name = "seq", sequenceName = "user_roles_id_seq", allocationSize = 1)
	@Column(name="ID",nullable = false)
	private Long id;

	@JsonIgnore
	@Column(name="user_id",nullable = false)
	private Long userId;

	@JsonIgnore
	@Column(name="role_id",nullable = false)
	private Long roleId;

	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "role_id", insertable = false, updatable = false)
	private Roles role;
	
	


}
