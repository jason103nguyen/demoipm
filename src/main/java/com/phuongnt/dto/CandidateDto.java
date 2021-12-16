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
	
	private int round;
	
	private Date timeInterview;
	
	private String sex;
	
	private Date birthDay;
	
	private String evaluation;
	
	private String note;
	
	private String result;
	
	private UserAppDto userApp;
	
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
		this.round = candicate.getRound();
		this.timeInterview = candicate.getTimeInterview();
		this.sex = candicate.getSex();
		this.birthDay = candicate.getBirthDay();
		this.evaluation = candicate.getEvaluation();
		this.note = candicate.getNote();
		this.result = candicate.getResult();
		
		UserAppDto userAppDto = new UserAppDto(candicate.getUserApp());
		this.userApp = userAppDto;
	}

	public UserAppDto getUserApp() {
		return userApp;
	}

	public void setUserApp(UserAppDto userApp) {
		this.userApp = userApp;
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

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public Date getTimeInterview() {
		return timeInterview;
	}

	public void setTimeInterview(Date timeInterview) {
		this.timeInterview = timeInterview;
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

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
