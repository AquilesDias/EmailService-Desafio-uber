package com.aquiles.serviceemail.controller;

import com.aquiles.serviceemail.application.EmailSenderService;
import com.aquiles.serviceemail.core.EmailRequest;
import com.aquiles.serviceemail.core.exceptions.EmailServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/email")
public class EmailSenderController {

    @Autowired
    private EmailSenderService emailSenderService;

    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request){
        try {
            this.emailSenderService.sendEmail( request.to(), request.subject(), request.body() );
            return ResponseEntity.ok("Email send successfull.");

        } catch (EmailServiceException ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error sending email.");
        }
    }
}
