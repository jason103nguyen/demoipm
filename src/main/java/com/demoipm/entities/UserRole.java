package com.demoipm.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.demoipm.dto.UserRoleDto;

@Entity
@Table(name = "user_role")
public class UserRole {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_role_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserApp userApp;
	
	@ManyToOne
	@JoinColumn(name = "role_id")
	private RoleApp roleApp;
	
	public UserRole() {}
	
	public UserRole(UserRoleDto userRoleDto) {
		super();
		
		UserApp userApp = new UserApp(userRoleDto.getUserApp());
		this.userApp = userApp;
		
		RoleApp roleApp = new RoleApp(userRoleDto.getRoleApp());
		this.roleApp = roleApp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserApp getUserApp() {
		return userApp;
	}

	public void setUserApp(UserApp userApp) {
		this.userApp = userApp;
	}

	public RoleApp getRoleApp() {
		return roleApp;
	}

	public void setRoleApp(RoleApp roleApp) {
		this.roleApp = roleApp;
	}
	
}
