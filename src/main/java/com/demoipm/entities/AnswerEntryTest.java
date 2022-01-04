package com.demoipm.entities;

import javax.persistence.*;

@Entity
@Table(name = "answer_entry_test")
public class AnswerEntryTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_entry_test_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntryTest question;

    @Column(name = "answer")
    private String answer;

    private boolean tick;

    public AnswerEntryTest(){

    }

    public AnswerEntryTest(QuestionEntryTest question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public QuestionEntryTest getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntryTest question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isTick() {
        return tick;
    }

    public void setTick(boolean tick) {
        this.tick = tick;
    }
}
