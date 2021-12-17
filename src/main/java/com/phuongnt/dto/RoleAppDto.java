package com.phuongnt.dto;

import java.util.ArrayList;
import java.util.List;

import com.phuongnt.entities.RoleApp;

public class RoleAppDto {

	private int id;
	
	private String roleName;
	
	private List<UserRoleDto> listUserRole = new ArrayList<UserRoleDto>();
	
	public RoleAppDto() {}
	
	public RoleAppDto(RoleApp roleApp) {
		super();
		this.roleName = roleApp.getRoleName();
	}

	public List<UserRoleDto> getListUserRole() {
		return listUserRole;
	}

	public void setListUserRole(List<UserRoleDto> listUserRole) {
		this.listUserRole = listUserRole;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
}
