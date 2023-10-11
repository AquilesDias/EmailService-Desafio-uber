package com.aquiles.serviceemail.infra.ses;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import com.aquiles.serviceemail.adapters.EmailSenderGateway;
import com.aquiles.serviceemail.core.exceptions.EmailServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SesEmailSender implements EmailSenderGateway {

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    @Autowired
    public SesEmailSender(AmazonSimpleEmailService amazonSimpleEmailService){
        this.amazonSimpleEmailService = amazonSimpleEmailService;
    }

    @Override
    public void senderEmail(String to, String subject, String body) {
        SendEmailRequest sendEmailRequest = new SendEmailRequest()
                .withSource("remetente@gmail.com")
                .withDestination(new Destination().withToAddresses(to) )
                .withMessage( new Message()
                        .withSubject( new Content(subject))
                        .withBody( new Body()
                                .withText( new Content(body) )
                        )
                );
        try {
            this.amazonSimpleEmailService.sendEmail(sendEmailRequest);
        } catch (AmazonServiceException ex){
            throw new EmailServiceException("failure sending email.", ex);
        }
    }
}
