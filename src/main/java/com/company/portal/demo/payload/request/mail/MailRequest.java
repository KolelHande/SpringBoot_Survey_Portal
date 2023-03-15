package com.company.portal.demo.payload.request.mail;

import com.company.portal.demo.enums.OperationTypeEnum;
import lombok.Data;

@Data
public class MailRequest {
    private String name;
    private String surname;
    private String recipient;
    private String messageBody;
    private String subject;
    private String attachment;
    private OperationTypeEnum operationType;
}
