package com.demoipm.dto;

import com.demoipm.entities.Candidate;
import com.demoipm.entities.JobSkill;
import com.demoipm.entities.QuestionEntryTest;

import javax.persistence.*;
import java.util.*;

public class EntryTestRequest {

    private int id;

    private Date timeEntryTest;

    private String result;

    private String point;

    private String nameTest;

    private String numberofQuestion;

    private Candidate candidate;

    private Set<QuestionEntryTest> questionEntryTest = new HashSet<QuestionEntryTest>();

    private List<JobSkill> listJobSkill = new ArrayList<JobSkill>();

    public EntryTestRequest(){

    }

    public EntryTestRequest(Date timeEntryTest, String result, String point, String nameTest, String numberofQuestion, Candidate candidate, Set<QuestionEntryTest> questionEntryTest, List<JobSkill> listJobSkill) {
        this.timeEntryTest = timeEntryTest;
        this.result = result;
        this.point = point;
        this.nameTest = nameTest;
        this.numberofQuestion = numberofQuestion;
        this.candidate = candidate;
        this.questionEntryTest = questionEntryTest;
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

    public String getNumberofQuestion() {
        return numberofQuestion;
    }

    public void setNumberofQuestion(String numberofQuestion) {
        this.numberofQuestion = numberofQuestion;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Set<QuestionEntryTest> getQuestionEntryTest() {
        return questionEntryTest;
    }

    public void setQuestionEntryTest(Set<QuestionEntryTest> questionEntryTest) {
        this.questionEntryTest = questionEntryTest;
    }

    public List<JobSkill> getListJobSkill() {
        return listJobSkill;
    }

    public void setListJobSkill(List<JobSkill> listJobSkill) {
        this.listJobSkill = listJobSkill;
    }

    @Override
    public String toString() {
        return "EntryTestRequest{" +
                "id=" + id +
                ", timeEntryTest=" + timeEntryTest +
                ", result='" + result + '\'' +
                ", point='" + point + '\'' +
                ", nameTest='" + nameTest + '\'' +
                ", numberofQuestion='" + numberofQuestion + '\'' +
                ", candidate=" + candidate +
                ", questionEntryTest=" + questionEntryTest +
                ", listJobSkill=" + listJobSkill +
                '}';
    }
}
