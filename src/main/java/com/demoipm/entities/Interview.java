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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "interview")
public class Interview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "interview_id")
	private int id;
	
	@Column(name = "time_interview")
	private Date timeInterview;
	
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

}
