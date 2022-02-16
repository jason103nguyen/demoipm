package com.demoipm.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.demoipm.dto.CandidateDto;
import com.demoipm.dto.InterviewDto;

@Entity
@Table(name = "interview")
public class Interview {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "interview_id")
	private int id;
	
	@Column(name = "round")
	private int round;
	
	@Column(name = "time_interview")
	private Date timeInterview;
	
	@Column(name = "local")
	private String local;
	
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
	
	public Interview() {}
	
	public Interview(InterviewDto interview) {
		super();
		this.id = interview.getId();
		this.timeInterview = interview.getTimeInterview();
		this.local = interview.getLocal();
		this.evaluation = interview.getEvaluation();
		this.note = interview.getNote();
		this.result = interview.getResult();
		this.nameInterviewer = interview.getNameInterviewer();
		
		Candidate candidate = new Candidate(interview.getCandidate());
		this.candidate = candidate;
		
		this.round = interview.getRound();
	}
	
	public InterviewDto convertToDto() {
		
		InterviewDto interviewDto = new InterviewDto();
		
		interviewDto.setId(this.id); 
		interviewDto.setTimeInterview(this.timeInterview); 
		interviewDto.setLocal(this.local);
		interviewDto.setEvaluation(this.evaluation);
		interviewDto.setNote(this.note);
		interviewDto.setResult(this.result);
		interviewDto.setNameInterviewer(this.nameInterviewer);
		
		CandidateDto candidateDto = new CandidateDto(this.candidate);
		interviewDto.setCandidate(candidateDto);
		
		interviewDto.setRound(this.round);
		
		return interviewDto;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTimeInterview() {
		return timeInterview;
	}

	public void setTimeInterview(Date timeInterview) {
		this.timeInterview = timeInterview;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
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
	
}
