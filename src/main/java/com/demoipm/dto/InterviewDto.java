package com.demoipm.dto;

import com.demoipm.entities.Interview;
import lombok.Data;
import org.springframework.util.ObjectUtils;

import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@Data
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

	public InterviewDto() {}

	public InterviewDto(Interview interview) {
		super();
		this.id = interview.getId();
		if (ObjectUtils.isEmpty(interview)) {
			this.timeInterview = toDate(interview.getTimeInterview());
		}
		this.location = interview.getLocation();
		this.evaluation = interview.getEvaluation();
		this.note = interview.getNote();
		this.result = interview.getResult();
		this.nameInterviewer = interview.getNameInterviewer();

		CandidateDto candidateDto = new CandidateDto(interview.getCandidate());
		this.candidate = candidateDto;

		this.round = interview.getRound();
	}

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

	/**
	 *
	 * @param localTime
	 * @return
	 */
	private Date toDate(LocalTime localTime) {
		Instant instant = localTime.atDate(LocalDate.now())
				.atZone(ZoneId.systemDefault()).toInstant();
		return toDate(instant);
	}

	/**
	 *
	 * @param instant
	 * @return
	 */
	private Date toDate(Instant instant) {
		BigInteger milis = BigInteger.valueOf(instant.getEpochSecond()).multiply(
				BigInteger.valueOf(1000));
		milis = milis.add(BigInteger.valueOf(instant.getNano()).divide(
				BigInteger.valueOf(1_000_000)));
		return new Date(milis.longValue());
	}
}
