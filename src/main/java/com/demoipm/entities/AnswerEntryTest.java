package com.demoipm.entities;

import javax.persistence.*;

@Entity
@Table(name = "answer_entry_test")
public class AnswerEntryTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_entry_test_id")
    private Integer id;

    /*@ManyToOne
    @JoinColumn(name = "question_id")
    private QuestionEntryTest question;*/

    @Column(name = "content")
    private String content;

    private boolean correct;

    public AnswerEntryTest(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AnswerEntryTest(boolean correct) {
        this.correct = correct;
    }
}
