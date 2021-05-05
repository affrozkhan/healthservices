package com.health.controller.api.dataexchange.response;

import java.util.List;

import com.health.controller.api.entity.Menus;
import com.health.controller.api.entity.Roles;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

	private Long userId;
	private String userName;
	private List<Roles> roles;
	private List<Menus>menus;
	
	public UserResponse(Long userId,String userName, List<Roles> roles, List<Menus> menus) {
		this.userId=userId;
		this.userName = userName;
		this.roles = roles;
		this.menus = menus;
	}
	
	
	
	
}
