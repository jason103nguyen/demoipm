package com.demoipm.entities;

import java.util.*;

import javax.persistence.*;

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
	
	@Column(name = "result")
	private String result;
	
	@Column(name = "point")
	private int point;
	
	@Column(name = "name_test")
	private String nameTest;

	@Column(name = "number_of_question")
	private String numberofQuestion;
	
	@ManyToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;

	@OneToMany(mappedBy = "entryTest", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<EntryTestQuestion> entryTestQuestions = new HashSet<EntryTestQuestion>();

	@OneToMany(mappedBy = "entryTest")
	private List<JobSkill> listJobSkill = new ArrayList<JobSkill>();
	
	public EntryTest() {}

	public EntryTest(Date timeEntryTest, String result, int point, String nameTest, String numberofQuestion, Candidate candidate, Set<EntryTestQuestion> entryTestQuestions, List<JobSkill> listJobSkill) {
		this.timeEntryTest = timeEntryTest;
		this.result = result;
		this.point = point;
		this.nameTest = nameTest;
		this.numberofQuestion = numberofQuestion;
		this.candidate = candidate;
		this.entryTestQuestions = entryTestQuestions;
		this.listJobSkill = listJobSkill;
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

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	public String getNumberofQuestion() {
		return numberofQuestion;
	}

	public void setNumberofQuestion(String numberofQuestion) {
		this.numberofQuestion = numberofQuestion;
	}

	public Set<EntryTestQuestion> getEntryTestQuestions() {
		return entryTestQuestions;
	}

	public void setEntryTestQuestions(Set<EntryTestQuestion> entryTestQuestions) {
		this.entryTestQuestions = entryTestQuestions;
	}

	public List<JobSkill> getListJobSkill() {
		return listJobSkill;
	}

	public void setListJobSkill(List<JobSkill> listJobSkill) {
		this.listJobSkill = listJobSkill;
	}
}
