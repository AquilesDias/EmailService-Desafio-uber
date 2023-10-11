package com.aquiles.serviceemail.adapters;

public interface EmailSenderGateway {

    void senderEmail(String to, String subject, String body);
}
