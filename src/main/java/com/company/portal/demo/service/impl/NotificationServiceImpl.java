package com.company.portal.demo.service.impl;

import com.company.portal.demo.entity.User;
import com.company.portal.demo.enums.OperationTypeEnum;
import com.company.portal.demo.payload.dto.MessageTemplateDto;
import com.company.portal.demo.payload.dto.OperationDto;
import com.company.portal.demo.payload.request.mail.MailRequest;
import com.company.portal.demo.repository.UserRepository;
import com.company.portal.demo.service.MessageTemplateService;
import com.company.portal.demo.service.NotificationService;
import com.company.portal.demo.service.OperationService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final JavaMailSender javaMailSender;
    private final OperationService operationService;
    private final MessageTemplateService messageTemplateService;
    private final UserRepository userRepository;

    @Override
    public String sendNotification(MailRequest mailRequest) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        OperationDto operationDto= operationService.getOperationByName(mailRequest.getOperationType());
        MessageTemplateDto message = messageTemplateService.getMessageByOperationId(operationDto.getId());

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(message.getRecipient());
            mimeMessageHelper.setText(message.getMessage());
            mimeMessageHelper.setSubject(message.getSubject());

            if(Objects.nonNull(mailRequest.getAttachment())) {

                FileSystemResource file = new FileSystemResource(new File(mailRequest.getAttachment()));
                mimeMessageHelper.addAttachment(file.getFilename(), file);
            }

            javaMailSender.send(mimeMessage);

            return "Mail sent Successfully";

        } catch (MessagingException e) {

            return "Error while sending mail!!!";

        }
    }

    @Override
    public void sendPasswordResetMail(String email) throws MessagingException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        userRepository.save(user);
        String resetLink = "http://localhost:8080/reset-password?token=" + token;

        MailRequest mailRequest = new MailRequest();
        mailRequest.setRecipient(email);
        mailRequest.setOperationType(OperationTypeEnum.RESET_PASSWORD_SEND_EMAIL);

        //linki {} bodye ekle.
        mailRequest.setMessageBody(resetLink);

        sendNotification(mailRequest);
    }

}
