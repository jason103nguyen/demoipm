package com.demoipm.entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "question_entry_test")
public class QuestionEntryTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_entry_test_id")
    private int id;

    @Column(name = "question")
    private String question;

    @Column(name = "level")
    private String level;

    @OneToMany(mappedBy = "question")
    private List<AnswerEntryTest> answerEntryTestList;

    @OneToOne
    private AnswerEntryTest answerCorrect;

    @ManyToOne
    @JoinColumn(name = "entry_test_id")
    private EntryTest entryTest;

    private Boolean tick;

    public QuestionEntryTest(){

    }

    public QuestionEntryTest(String question, String level, List<AnswerEntryTest> answerEntryTestList, AnswerEntryTest answerCorrect, EntryTest entryTest) {
        this.question = question;
        this.level = level;
        this.answerEntryTestList = answerEntryTestList;
        this.answerCorrect = answerCorrect;
        this.entryTest = entryTest;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public List<AnswerEntryTest> getAnswerEntryTestList() {
        return answerEntryTestList;
    }

    public void setAnswerEntryTestList(List<AnswerEntryTest> answerEntryTestList) {
        this.answerEntryTestList = answerEntryTestList;
    }

    public AnswerEntryTest getAnswerCorrect() {
        return answerCorrect;
    }

    public void setAnswerCorrect(AnswerEntryTest answerCorrect) {
        this.answerCorrect = answerCorrect;
    }

    public EntryTest getEntryTest() {
        return entryTest;
    }

    public void setEntryTest(EntryTest entryTest) {
        this.entryTest = entryTest;
    }

    public Boolean getTick() {
        return tick;
    }

    public void setTick(Boolean tick) {
        this.tick = tick;
    }
}
