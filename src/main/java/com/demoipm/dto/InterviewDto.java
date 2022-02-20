package com.demoipm.dto;

import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
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
