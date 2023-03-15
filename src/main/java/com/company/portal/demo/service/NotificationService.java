package com.company.portal.demo.service;

import com.company.portal.demo.payload.request.mail.MailRequest;

import javax.mail.MessagingException;

public interface NotificationService {
    String sendNotification(MailRequest user);

    void sendPasswordResetMail(String email) throws MessagingException;
}
