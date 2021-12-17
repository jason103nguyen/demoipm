package com.phuongnt.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.phuongnt.dto.CandidateDto;
import com.phuongnt.dto.UserAppDto;

@Entity
@Table(name = "user_app")
public class UserApp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private String role;
	
	public UserApp() {}
	
	public UserApp(UserAppDto userAppDto) {
		super();
		this.id = userAppDto.getId();
		this.fullName = userAppDto.getFullName();
		this.phone = userAppDto.getPhone();
		this.email = userAppDto.getEmail();
		this.username = userAppDto.getUsername();
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encrytedPassword = encoder.encode(userAppDto.getPassword());
		this.password = encrytedPassword;
		
		this.role = userAppDto.getRole();
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
