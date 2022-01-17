package com.demoipm.entities;

import javax.persistence.*;

@Entity
@Table(name = "entry_test_question")
public class EntryTestQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entry_test_question_id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "entry_test_id")
    private EntryTest entryTest;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "question_entry_test_id")
    private Question questionEntryTest;

}
