package com.demoipm.entities;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.demoipm.dto.InterviewDto;

@Entity
@Table(name = "interview")
public class Interview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "interview_id")
	private int id;

	@Column(name = "time_interview")
	private LocalTime timeInterview;

	@Column(name = "location")
	private String location;

	@Column(name = "evaluation")
	private String evaluation;

	@Column(name = "note")
	private String note;

	@Column(name = "result")
	private String result;

	@Column(name = "name_interviewer")
	private String nameInterviewer;

	@ManyToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;

	@Column(name = "contact_form")
	private String contactForm;

	@Column(name = "date")
	protected Date date;
	
	public Interview() {}

	public Interview(LocalTime timeInterview, String location, String evaluation, String note, String result, String nameInterviewer, Candidate candidate, String contactForm, Date date) {
		this.timeInterview = timeInterview;
		this.location = location;
		this.evaluation = evaluation;
		this.note = note;
		this.result = result;
		this.nameInterviewer = nameInterviewer;
		this.candidate = candidate;
		this.contactForm = contactForm;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalTime getTimeInterview() {
		return timeInterview;
	}

	public void setTimeInterview(LocalTime timeInterview) {
		this.timeInterview = timeInterview;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public String getNameInterviewer() {
		return nameInterviewer;
	}

	public void setNameInterviewer(String nameInterviewer) {
		this.nameInterviewer = nameInterviewer;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public String getContactForm() {
		return contactForm;
	}

	public void setContactForm(String contactForm) {
		this.contactForm = contactForm;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
