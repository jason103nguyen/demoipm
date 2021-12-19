package com.demoipm.dto;

import com.demoipm.entities.UserRole;

public class UserRoleDto {

	private int id;
	
	private UserAppDto userApp;
	
	private RoleAppDto roleApp;
	
	public UserRoleDto() {}
	
	public UserRoleDto(UserRole userRole) {
		super();
		
		UserAppDto userAppDto = new UserAppDto(userRole.getUserApp());
		this.userApp = userAppDto;
		
		RoleAppDto roleAppDto = new RoleAppDto(userRole.getRoleApp());
		this.roleApp = roleAppDto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserAppDto getUserApp() {
		return userApp;
	}

	public void setUserApp(UserAppDto userApp) {
		this.userApp = userApp;
	}

	public RoleAppDto getRoleApp() {
		return roleApp;
	}

	public void setRoleApp(RoleAppDto roleApp) {
		this.roleApp = roleApp;
	}

}
