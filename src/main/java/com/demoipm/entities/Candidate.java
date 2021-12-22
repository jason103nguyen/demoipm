package com.demoipm.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.demoipm.dto.CandidateDto;

@Entity
@Table(name = "candidate")
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "candidate_id")
	private int id;
	
	@Column(name = "activity")
	private String activity;
	
	@Column(name = "experience_year")
	private Integer experienceYear;
	
	@Column(name = "skill")
	private String skill;
	
	@Column(name = "full_name")
	private String fullName;
	
	@Column(name = "cmnd")
	private String cmnd;
	
	@Column(name = "date_cmnd")
	private Date dateCmnd;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "info")
	private String info;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "sex")
	private String sex;
	
	@Column(name = "birth_day")
	private Date birthDay;
	
	@OneToMany(mappedBy = "candidate")
	private List<Interview> listInterview = new ArrayList<Interview>();
	
	@OneToMany(mappedBy = "candidate")
	private List<EntryTest> listEntryTest = new ArrayList<EntryTest>();
	
	public Candidate() {}
	
	public Candidate(CandidateDto candidateDto) {
		super();
		this.id = candidateDto.getId();
		this.fullName = candidateDto.getFullName();
		this.cmnd = candidateDto.getCmnd();
		this.dateCmnd = candidateDto.getDateCmnd();
		this.phone = candidateDto.getPhone();
		this.email = candidateDto.getEmail();
		this.info = candidateDto.getInfo();
		this.status = candidateDto.getStatus();
		this.sex = candidateDto.getSex();
		this.birthDay = candidateDto.getBirthDay();
		this.skill = candidateDto.getSkill();
		this.experienceYear = candidateDto.getExperienceYear();
		this.activity = candidateDto.getActivity();
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public Integer getExperienceYear() {
		return experienceYear;
	}

	public void setExperienceYear(Integer experienceYear) {
		this.experienceYear = experienceYear;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public List<Interview> getListInterview() {
		return listInterview;
	}

	public void setListInterview(List<Interview> listInterview) {
		this.listInterview = listInterview;
	}

	public List<EntryTest> getListEntryTest() {
		return listEntryTest;
	}

	public void setListEntryTest(List<EntryTest> listEntryTest) {
		this.listEntryTest = listEntryTest;
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

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public Date getDateCmnd() {
		return dateCmnd;
	}
	
	public void setDateCmnd(Date dateCmnd) {
		this.dateCmnd = dateCmnd;
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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

}
