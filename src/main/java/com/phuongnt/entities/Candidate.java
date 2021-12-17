package com.phuongnt.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	
	@Column(name = "round")
	private int round;
	
	@Column(name = "time_interview")
	private Date timeInterview;
	
	@Column(name = "sex")
	private String sex;
	
	@Column(name = "birth_day")
	private Date birthDay;
	
	@Column(name = "evaluation")
	private String evaluation;
	
	@Column(name = "note")
	private String note;
	
	@Column(name = "result")
	private String result;
	
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
		this.round = candicateDto.getRound();
		this.timeInterview = candicateDto.getTimeInterview();
		this.sex = candicateDto.getSex();
		this.birthDay = candicateDto.getBirthDay();
		this.evaluation = candicateDto.getEvaluation();
		this.note = candicateDto.getNote();
		this.result = candicateDto.getResult();
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
