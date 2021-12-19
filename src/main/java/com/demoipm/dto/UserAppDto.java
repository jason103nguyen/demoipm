package com.demoipm.dto;

import java.util.ArrayList;
import java.util.List;

import com.demoipm.entities.UserApp;

public class UserAppDto {

	private int id;
	
	private String fullName;
	
	private String phone;
	
	private String email;
	
	private String username;
	
	private String password;
	
	private String role;
	
	private List<UserRoleDto> listUserRole = new ArrayList<UserRoleDto>();
	
	public UserAppDto() {}
	
	public UserAppDto(UserApp userApp) {
		super();
		this.id = userApp.getId();
		this.fullName = userApp.getFullName();
		this.phone = userApp.getPhone();
		this.email = userApp.getEmail();
		this.username = userApp.getUsername();
		this.password = userApp.getPassword();
		this.role = userApp.getRole();
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
