package com.demoipm.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.SortableField;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.demoipm.dto.UserAppDto;

@Entity
@Table(name = "user_app")
@SQLDelete(sql = "UPDATE user_app SET is_delete = true WHERE user_id = ?")
@Where(clause = "is_delete = false")
@Indexed
public class UserApp extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;

	@Field
	@Column(name = "full_name")
	private String fullName;

	@Field
	@Column(name = "phone")
	private String phone;

	@Field
	@Column(name = "email")
	private String email;

	@Field
    @SortableField
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private String role;
	
	@OneToMany(mappedBy = "userApp")
	private List<UserRole> listUserRole = new ArrayList<UserRole>();
	
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
	
	public List<UserRole> getListUserRole() {
		return listUserRole;
	}

	public void setListUserRole(List<UserRole> listUserRole) {
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
