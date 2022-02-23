package com.demoipm.email.controller;

import com.demoipm.consts.URLConst;
import com.demoipm.email.config.EmailConfig;
import com.demoipm.email.model.EmailRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
public class EmailController {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmailController.class);

    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

    @Autowired
    public JavaMailSender emailSender;

    @PostMapping(URLConst.API_SEND_EMAIL)
    public String sendEmail(@RequestBody EmailRequest request) {
        LOGGER.info("Start get send email with: ");
        System.out.println(request);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(request.getTo());
        message.setSubject(request.getSubject());
        message.setText(request.getMessage());
        if(request.getCc() != null) {
            message.setCc(request.getCc());
        }
        if (request.getBcc() != null) {
            message.setBcc(request.getBcc());
        }
        emailSender.send(message);
        LOGGER.info("End send email!!");
        return "Send email successful...";
    }

    @PostMapping(URLConst.API_SEND_EMAIL_ATTACHMENT)
    public String sendEmailAttachment(@RequestBody EmailRequest request) throws MessagingException, UnsupportedEncodingException {
        LOGGER.info("Start get send email attachment with: ");
        System.out.println(request);
        MimeMessage message = emailSender.createMimeMessage();
        boolean multipart = true;
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart);
        helper.setTo(request.getTo());
        helper.setSubject(request.getSubject());
        helper.setText(request.getMessage());
        if(request.getCc() != null) {
            helper.setCc(request.getCc());
        }
        if (request.getBcc() != null) {
            helper.setBcc(request.getBcc());
        }
        for (Object path: request.getUploadFile()) {
            FileSystemResource file = new FileSystemResource(new File(path.toString()));
            helper.addAttachment(file.getFilename(), file);
        }
        emailSender.send(message);
        LOGGER.info("End send email attachment!!");
        return "Send email with attachment successful...";
    }

    @PostMapping(URLConst.API_SEND_EMAIL_HTML)
    public String sendEmailHTML(@RequestBody EmailRequest request) throws MessagingException {
        LOGGER.info("Start get send email html style with: ");
        System.out.println(request);
        MimeMessage message = emailSender.createMimeMessage();
        boolean multipart = true;
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
        message.setContent(request.getMessage(), "text/html");
        helper.setTo(request.getTo());
        helper.setSubject(request.getSubject());
        if(request.getCc() != null) {
            helper.setCc(request.getCc());
        }
        if (request.getBcc() != null) {
            helper.setBcc(request.getBcc());
        }
        emailSender.send(message);
        LOGGER.info("End send email htmt style!!");
        return "Send email with html successful...";
    }
}
