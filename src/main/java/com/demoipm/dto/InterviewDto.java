package com.demoipm.dto;

import java.util.Date;

import com.demoipm.entities.Candidate;
import com.demoipm.entities.Interview;
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


	
}
