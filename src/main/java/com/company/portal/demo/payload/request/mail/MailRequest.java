package com.company.portal.demo.payload.request.mail;

import com.company.portal.demo.enums.OperationTypeEnum;
import com.company.portal.demo.payload.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MailRequest {
    private UserDto user;
    private String recipient;
    private String messageBody;
    private String subject;
    private String attachment;
    private OperationTypeEnum operationType;
}
