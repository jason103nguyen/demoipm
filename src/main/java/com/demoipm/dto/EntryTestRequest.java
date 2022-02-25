package com.demoipm.dto;

import com.demoipm.entities.Candidate;
import com.demoipm.entities.JobSkill;
import com.demoipm.entities.Question;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
import java.util.*;

public class EntryTestRequest {

    private Integer id;

    private Integer questionId;

    private List<Integer> questionIds;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTest;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date finishTest;

    private String timeEntryTest;

    private String timeEntryTest2;

    private String result;

    private int point;

    private String nameTest;

    private Integer numberOfQuestion;

    private Candidate candidate;

    private List<Question> questions = new ArrayList<Question>();

    private List<JobSkill> listJobSkill = new ArrayList<JobSkill>();

    public EntryTestRequest(){

    }


    public EntryTestRequest(Integer questionId, List<Integer> questionIds, Date beginTest,
        Date finishTest, String timeEntryTest, String timeEntryTest2, String result,
        int point,
        String nameTest, Integer numberOfQuestion, Candidate candidate,
        List<Question> questions, List<JobSkill> listJobSkill) {
        this.questionId = questionId;
        this.questionIds = questionIds;
        this.beginTest = beginTest;
        this.finishTest = finishTest;
        this.timeEntryTest = timeEntryTest;
        this.timeEntryTest2 = timeEntryTest2;
        this.result = result;
        this.point = point;
        this.nameTest = nameTest;
        this.numberOfQuestion = numberOfQuestion;
        this.candidate = candidate;
        this.questions = questions;
        this.listJobSkill = listJobSkill;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimeEntryTest() {
        return timeEntryTest;
    }

    public void setTimeEntryTest(String timeEntryTest) {
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

    public String getTimeEntryTest2() {
        return timeEntryTest2;
    }

    public void setTimeEntryTest2(String timeEntryTest2) {
        this.timeEntryTest2 = timeEntryTest2;
    }

    public Integer getNumberOfQuestion() {
        return numberOfQuestion;
    }

    public void setNumberOfQuestion(Integer numberOfQuestion) {
        this.numberOfQuestion = numberOfQuestion;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
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

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public List<Integer> getQuestionIds() {
        return questionIds;
    }

    public void setQuestionIds(List<Integer> questionIds) {
        this.questionIds = questionIds;
    }

    @Override
    public String toString() {
        return "EntryTestRequest{" +
            "id=" + id +
            ", questionId=" + questionId +
            ", questionIds=" + questionIds +
            ", beginTest=" + beginTest +
            ", finishTest=" + finishTest +
            ", timeEntryTest=" + timeEntryTest +
            ", timeEntryTest2=" + timeEntryTest2 +
            ", result='" + result + '\'' +
            ", point=" + point +
            ", nameTest='" + nameTest + '\'' +
            ", numberOfQuestion=" + numberOfQuestion +
            ", candidate=" + candidate +
            ", questions=" + questions +
            ", listJobSkill=" + listJobSkill +
            '}';
    }
}
