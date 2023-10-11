package com.aquiles.serviceemail.core;

public record EmailRequest(String to, String subject, String body) {
}
