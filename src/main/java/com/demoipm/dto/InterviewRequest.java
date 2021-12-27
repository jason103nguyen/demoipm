package com.demoipm.dto;

import com.demoipm.entities.Candidate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalTime;
import java.util.Date;



public class InterviewRequest {

    private Integer id;

    private Candidate candidate;

    @NotBlank
    protected LocalTime timeInterview;

    @NotBlank
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    protected Date date;

    @NotBlank
    private String location;

    private String nameInterviewer;

    private String contactForm;

    public InterviewRequest(){

    }

    public InterviewRequest(Candidate candidate, LocalTime timeInterview, Date date, String location, String nameInterviewer, String contactForm) {
        this.candidate = candidate;
        this.timeInterview = timeInterview;
        this.date = date;
        this.location = location;
        this.nameInterviewer = nameInterviewer;
        this.contactForm = contactForm;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public LocalTime getTimeInterview() {
        return timeInterview;
    }

    public void setTimeInterview(LocalTime timeInterview) {
        this.timeInterview = timeInterview;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNameInterviewer() {
        return nameInterviewer;
    }

    public void setNameInterviewer(String nameInterviewer) {
        this.nameInterviewer = nameInterviewer;
    }

    public String getContactForm() {
        return contactForm;
    }

    public void setContactForm(String contactForm) {
        this.contactForm = contactForm;
    }

    @Override
    public String toString() {
        return "InterviewRequest{" +
                "id=" + id +
                ", candidate=" + candidate +
                ", timeInterview=" + timeInterview +
                ", date=" + date +
                ", location='" + location + '\'' +
                ", nameInterviewer='" + nameInterviewer + '\'' +
                ", contactForm='" + contactForm + '\'' +
                '}';
    }
}
