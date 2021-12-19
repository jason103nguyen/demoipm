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

import com.demoipm.dto.EntryTestDto;

@Entity
@Table(name = "entry_test")
public class EntryTest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "entry_test_id")
	private int id;
	
	@Column(name = "time_entry_test")
	private Date timeEntryTest;
	
	@Column(name = "local")
	private String local;
	
	@Column(name = "result")
	private String result;
	
	@Column(name = "point")
	private String point;
	
	@Column(name = "name_test")
	private String nameTest;
	
	@ManyToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;
	
	public EntryTest() {}
	
	public EntryTest(EntryTestDto entryTest) {
		super();
		this.id = entryTest.getId();
		this.timeEntryTest = entryTest.getTimeEntryTest();
		this.local = entryTest.getLocal();
		this.result = entryTest.getResult();
		this.point = entryTest.getPoint();
		this.nameTest = entryTest.getNameTest();
		
		Candidate candidate = new Candidate(entryTest.getCandidate());
		this.candidate = candidate;
	}

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

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getNameTest() {
		return nameTest;
	}

	public void setNameTest(String nameTest) {
		this.nameTest = nameTest;
	}

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

}
