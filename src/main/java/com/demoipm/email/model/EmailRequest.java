package com.demoipm.email.model;

import java.util.List;

public class EmailRequest {

    private String senderEmail;
    private String senderPassword;
    private String to;
    private String subject;
    private String message;
    private String cc;
    private String bcc;
    private List<Object> uploadFile;

    public String getSenderEmail() {
        return senderEmail;
    }

    public EmailRequest setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
        return this;
    }

    public String getSenderPassword() {
        return senderPassword;
    }

    public EmailRequest setSenderPassword(String senderPassword) {
        this.senderPassword = senderPassword;
        return this;
    }

    public String getTo() {
        return to;
    }

    public EmailRequest setTo(String to) {
        this.to = to;
        return this;
    }

    public String getSubject() {
        return subject;
    }

    public EmailRequest setSubject(String subject) {
        this.subject = subject;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public EmailRequest setMessage(String message) {
        this.message = message;
        return this;
    }

    public List<Object> getUploadFile() {
        return uploadFile;
    }

    public EmailRequest setUploadFile(List<Object> uploadFile) {
        this.uploadFile = uploadFile;
        return this;
    }

    public String getCc() {
        return cc;
    }

    public EmailRequest setCc(String cc) {
        this.cc = cc;
        return this;
    }

    public String getBcc() {
        return bcc;
    }

    public EmailRequest setBcc(String bcc) {
        this.bcc = bcc;
        return this;
    }

    @Override
    public String toString() {
        return "EmailRequest{" +
                "senderEmail='" + senderEmail + '\'' +
                ", senderPassword='" + senderPassword + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", cc='" + cc + '\'' +
                ", bcc='" + bcc + '\'' +
                ", uploadFile=" + uploadFile +
                '}';
    }
}
