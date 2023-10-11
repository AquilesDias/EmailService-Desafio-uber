package com.aquiles.serviceemail.application;

import com.aquiles.serviceemail.adapters.EmailSenderGateway;
import com.aquiles.serviceemail.core.EmailSenderUseCase;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailSenderService implements EmailSenderUseCase {

    private final EmailSenderGateway emailSenderGateway;

    @Autowired
    public EmailSenderService(EmailSenderGateway emailSenderGateway){
        this.emailSenderGateway = emailSenderGateway;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        this.emailSenderGateway.senderEmail(to, subject, body);
    }
}
