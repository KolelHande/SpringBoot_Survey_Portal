package com.company.portal.demo.service.impl;

import com.company.portal.demo.exception.RTBusinessException;
import com.company.portal.demo.payload.dto.MessageTemplateDto;
import com.company.portal.demo.payload.dto.OperationDto;
import com.company.portal.demo.payload.request.mail.MailRequest;
import com.company.portal.demo.service.MessageTemplateService;
import com.company.portal.demo.service.NotificationService;
import com.company.portal.demo.service.OperationService;
import com.company.portal.demo.util.parameter.MessageParameterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final JavaMailSender javaMailSender;
    private final OperationService operationService;
    private final MessageTemplateService messageTemplateService;
    private final MessageParameterUtil messageParameterUtil;

    @Override
    public String sendNotification(MailRequest mailRequest) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        OperationDto operationDto = operationService.getOperationByName(mailRequest.getOperationType());
        MessageTemplateDto message = messageTemplateService.getMessageByOperationId(operationDto.getId());

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setTo(Objects.nonNull(mailRequest.getRecipient()) ? mailRequest.getRecipient() : message.getRecipient());
            mimeMessageHelper.setText(messageParameterUtil.generateTextContent(mailRequest.getUser(), message.getMessage()));
            mimeMessageHelper.setSubject(message.getSubject());

            if (Objects.nonNull(mailRequest.getAttachment())) {
                FileSystemResource file = new FileSystemResource(new File(mailRequest.getAttachment()));
                mimeMessageHelper.addAttachment(file.getFilename(), file);
            }

            javaMailSender.send(mimeMessage);

            return "Mail sent Successfully";

        } catch (MessagingException e) {

            throw new RTBusinessException("Error while sending mail!!!" + e.getMessage());
        }
    }
}
