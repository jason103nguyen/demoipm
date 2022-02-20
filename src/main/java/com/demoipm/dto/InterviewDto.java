package com.demoipm.dto;

import java.util.Date;

import com.demoipm.entities.Candidate;
import com.demoipm.entities.Interview;

public class InterviewDto {

	private int id;
	
	private int round;
	
	private Date timeInterview;
	
	private String location;
	
	private String evaluation;
	
	private String note;
	
	private String result;
	
	private String nameInterviewer;

	private CandidateDto candidate;

/*
	public InterviewDto(Interview interview) {
		super();
		this.id = interview.getId();
		this.timeInterview = interview.getTimeInterview();
		this.location = interview.getLocation();
		this.evaluation = interview.getEvaluation();
		this.note = interview.getNote();
		this.result = interview.getResult();
		this.nameInterviewer = interview.getNameInterviewer();
		
		CandidateDto candidateDto = new CandidateDto(interview.getCandidate());
		this.candidate = candidateDto;
		
		this.round = interview.getRound();
	}
	
	public Interview convertToEntity() {
		
		Interview interview = new Interview();
		
		interview.setId(this.id); 
		interview.setTimeInterview(this.timeInterview); 
		interview.setLocal(this.local);
		interview.setEvaluation(this.evaluation);
		interview.setNote(this.note);
		interview.setResult(this.result);
		interview.setNameInterviewer(this.nameInterviewer);
		
		Candidate candidate = new Candidate(this.candidate);
		interview.setCandidate(candidate);
		
		interview.setRound(this.round);
		
		return interview;
	}

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}
*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public CandidateDto getCandidate() {
		return candidate;
	}

	public void setCandidate(CandidateDto candidate) {
		this.candidate = candidate;
	}
}
