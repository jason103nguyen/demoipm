package com.phuongnt.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.phuongnt.dto.CandidateDto;

@Entity
@Table(name = "candidate")
public class Candidate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "candidate_id")
	private int id;
	
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
	
	@OneToOne
	@JoinColumn(name = "interview_id")
	private Interview interview;
	
	@OneToOne
	@JoinColumn(name = "entry_test_id")
	private EntryTest entryTest;
	
	public Candidate() {}
	
	public Candidate(CandidateDto candicateDto) {
		super();
		this.id = candicateDto.getId();
		this.fullName = candicateDto.getFullName();
		this.cmnd = candicateDto.getCmnd();
		this.dateCmnd = candicateDto.getDateCmnd();
		this.phone = candicateDto.getPhone();
		this.email = candicateDto.getEmail();
		this.info = candicateDto.getInfo();
		this.status = candicateDto.getStatus();
		this.sex = candicateDto.getSex();
		this.birthDay = candicateDto.getBirthDay();
		
		Interview interview = new Interview(candicateDto.getInterview());
		this.interview = interview;
		
		EntryTest entryTest = new EntryTest(candicateDto.getEntryTest());
		this.entryTest = entryTest;
	}

	public EntryTest getEntryTest() {
		return entryTest;
	}

	public void setEntryTest(EntryTest entryTest) {
		this.entryTest = entryTest;
	}

	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
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
