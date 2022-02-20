package com.demoipm.entities;

import java.time.LocalTime;
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

	@Column(name="begin_test")
	private Date beginTest;

	@Column(name="finish_test")
	private Date finishTest;
	
	@Column(name = "time_entry_test")
	private LocalTime timeEntryTest;
	
	@Column(name = "result")
	private String result;
	
	@Column(name = "point")
	private int point;
	
	@Column(name = "name_test")
	private String nameTest;

	@Column(name = "number_of_question")
	private Integer numberofQuestion;
	
	@ManyToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;

	@ManyToMany
	@JoinTable(name = "entrytest_question",
	        joinColumns = @JoinColumn(name = "entry_test_id"),
			inverseJoinColumns = @JoinColumn(name = "question_id"))
	private List<Question> questions = new ArrayList<Question>();

	@OneToMany(mappedBy = "entryTest")
	private List<JobSkill> listJobSkill = new ArrayList<JobSkill>();
	
	public EntryTest() {}

	public EntryTest(Date beginTest, Date finishTest, LocalTime timeEntryTest, String result, int point, String nameTest, Integer numberofQuestion, Candidate candidate, List<Question> questions, List<JobSkill> listJobSkill) {
		this.beginTest = beginTest;
		this.finishTest = finishTest;
		this.timeEntryTest = timeEntryTest;
		this.result = result;
		this.point = point;
		this.nameTest = nameTest;
		this.numberofQuestion = numberofQuestion;
		this.candidate = candidate;
		this.questions = questions;
		this.listJobSkill = listJobSkill;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalTime getTimeEntryTest() {
		return timeEntryTest;
	}

	public void setTimeEntryTest(LocalTime timeEntryTest) {
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

	public Integer getNumberofQuestion() {
		return numberofQuestion;
	}

	public void setNumberofQuestion(Integer numberofQuestion) {
		this.numberofQuestion = numberofQuestion;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public List<JobSkill> getListJobSkill() {
		return listJobSkill;
	}

	public void setListJobSkill(List<JobSkill> listJobSkill) {
		this.listJobSkill = listJobSkill;
	}

	public Date getBeginTest() {
		return beginTest;
	}

	public void setBeginTest(Date beginTest) {
		this.beginTest = beginTest;
	}

	public Date getFinishTest() {
		return finishTest;
	}

	public void setFinishTest(Date finishTest) {
		this.finishTest = finishTest;
	}

}
