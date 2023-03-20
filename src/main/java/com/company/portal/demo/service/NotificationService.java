package com.company.portal.demo.service;

import com.company.portal.demo.payload.request.mail.MailRequest;

public interface NotificationService {
    String sendNotification(MailRequest user);
}
