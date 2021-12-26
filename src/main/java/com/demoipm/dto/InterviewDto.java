package com.demoipm.dto;

import java.util.Date;

import com.demoipm.entities.Interview;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InterviewDto {

	private int id;
	
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
	}
*/


	
}
