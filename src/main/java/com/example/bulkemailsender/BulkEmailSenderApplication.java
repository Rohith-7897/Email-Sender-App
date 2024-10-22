package com.example.bulkemailsender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.bulkemailsender.service.BulkEmailService;
import com.example.bulkemailsender.util.BulkExcelReader;

import jakarta.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import jakarta.mail.*;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class BulkEmailSenderApplication implements CommandLineRunner {

    @Autowired
    private BulkEmailService emailService;

    @Autowired
    private BulkExcelReader excelReader;

    public static void main(String[] args) {
        SpringApplication.run(BulkEmailSenderApplication.class, args);
    }

    @Override
    public void run(String... args) {
    	String filePath = "src/main/resources/email.xlsx"; // Update this path
        String attachmentPath = "src/main/resources/Rohith_Resume.pdf"; // Update this path
        
        String emailSubject = "Software Engineer (Java Developer) - Enthusiastic Fresher";  // Hardcoded subject
        String emailBody = "Dear Sir/Mam,<br>"
                + "<br>"
                + "I am Rohith,<strong> 2023 B.Tech graduate</strong> in <strong>Electronics and Communication Engineering</strong> with a strong foundation in <strong>Java</strong>, <strong>SQL</strong>, <strong>HTML</strong>, <strong>CSS</strong>, <strong>Hibernate</strong>, <strong>Spring</strong> and <strong>Spring Boot</strong>. I'm passionate about <strong>Software Development</strong> and seeking a <strong>Entry level Software Engineer role</strong>.<br>"
                + "<br>"
                + "My projects include a <strong>Personal Portfolio</strong> showcasing my skills, a <strong>User Management REST API</strong> with Spring Boot, and a <strong>Full-stack User Management System</strong> using ReactJS for front-end and SpringBoot for backend.<br>"
                + "<br>"
                + "I'm eager to contribute to your team and learn from experienced professionals.<br>"
                + "<br>"
                + "Please find my resume attached.<br>"
                + "<br>"
                + "Sincerely,<br>"
                + "Sareddu Rohith Venkat<br>"
                + "sareddurohith@gmail.com<br>"
                + "+91 9100217897";  // Hardcoded body
        
        try {
            List<String> emailAddresses = excelReader.readEmailAddresses(filePath);
            for (String email : emailAddresses) {
                emailService.sendEmailWithAttachment(email, emailSubject, emailBody, attachmentPath);
                System.out.println("Email sent to: " + email);
            }
        } catch (IOException | MessagingException e) {
        	System.err.println("Something went wrong");
            e.printStackTrace();
        } 
    }
}