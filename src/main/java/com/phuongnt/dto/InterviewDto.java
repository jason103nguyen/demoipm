package com.phuongnt.dto;

import java.util.Date;

import com.phuongnt.entities.Interview;

public class InterviewDto {

	private int id;
	
	private Date timeInterview;
	
	private String local;
	
	private String evaluation;
	
	private String note;
	
	private String result;
	
	private String nameInterviewer;

	private CandidateDto candidate;
	
	public InterviewDto() {}

	public InterviewDto(Interview interview) {
		super();
		this.id = interview.getId();
		this.timeInterview = interview.getTimeInterview();
		this.local = interview.getLocal();
		this.evaluation = interview.getEvaluation();
		this.note = interview.getNote();
		this.result = interview.getResult();
		this.nameInterviewer = interview.getNameInterviewer();
		
		CandidateDto candidateDto = new CandidateDto(interview.getCandidate());
		this.candidate = candidateDto;
	}

	public CandidateDto getCandidate() {
		return candidate;
	}

	public void setCandidate(CandidateDto candidate) {
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
