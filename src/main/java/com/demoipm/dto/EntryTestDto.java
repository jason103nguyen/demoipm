package com.demoipm.dto;

import java.util.Date;

import com.demoipm.entities.EntryTest;

public class EntryTestDto {

	private int id;
	
	private Date timeEntryTest;
	
	private String local;
	
	private String result;
	
	private int point;
	
	private String nameTest;
	
	private CandidateDto candidate;
	
	public EntryTestDto() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTimeEntryTest() {
		return timeEntryTest;
	}

	public void setTimeEntryTest(Date timeEntryTest) {
		this.timeEntryTest = timeEntryTest;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getNameTest() {
		return nameTest;
	}

	public void setNameTest(String nameTest) {
		this.nameTest = nameTest;
	}

	public CandidateDto getCandidate() {
		return candidate;
	}

	public void setCandidate(CandidateDto candidate) {
		this.candidate = candidate;
	}

}
