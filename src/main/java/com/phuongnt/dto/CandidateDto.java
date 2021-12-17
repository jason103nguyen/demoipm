package com.phuongnt.dto;

import java.util.Date;

import com.phuongnt.entities.Candidate;

public class CandidateDto {

	private int id;
	
	private String fullName;
	
	private String cmnd;
	
	private Date dateCmnd;
	
	private String phone;
	
	private String email;
	
	private String info;
	
	private String status;
	
	private String sex;
	
	private Date birthDay;
	
	private InterviewDto interview;
	
	private EntryTestDto entryTest;
	
	public CandidateDto() {}
	
	public CandidateDto(Candidate candicate) {
		super();
		this.id = candicate.getId();
		this.fullName = candicate.getFullName();
		cmnd = candicate.getCmnd();
		this.dateCmnd = candicate.getDateCmnd();
		this.phone = candicate.getPhone();
		this.email = candicate.getEmail();
		this.info = candicate.getInfo();
		this.status = candicate.getStatus();
		this.sex = candicate.getSex();
		this.birthDay = candicate.getBirthDay();
		
		InterviewDto interviewDto = new InterviewDto(candicate.getInterview());
		this.interview = interviewDto;
		
		EntryTestDto entryTestDto = new EntryTestDto(candicate.getEntryTest());
		this.entryTest = entryTestDto;
	}

	
	public InterviewDto getInterview() {
		return interview;
	}

	public void setInterview(InterviewDto interview) {
		this.interview = interview;
	}

	public EntryTestDto getEntryTest() {
		return entryTest;
	}

	public void setEntryTest(EntryTestDto entryTest) {
		this.entryTest = entryTest;
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
