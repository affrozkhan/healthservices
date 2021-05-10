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
@Table(name = "SYSTEM_MENU_ROLES")
@Getter
@Setter
public class RoleMenus extends AbstractColumnDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "system_menu_roles_id_seq")
	@SequenceGenerator(name = "system_menu_roles_id_seq", sequenceName = "system_menu_roles_id_seq", allocationSize = 1)
	@Column(name="ID",nullable = false)
	private Long id;

	@Column(name="menu_id")
	private Long menuId;

	@Column(name="role_id")
	private Long roleId;



}
