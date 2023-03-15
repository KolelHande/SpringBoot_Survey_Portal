package com.company.portal.demo.payload.dto;

import com.company.portal.demo.entity.Operation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MessageTemplateDto {

    private Operation operation;

    private String message;

    private String subject;

    private String recipient;

    private String recipientCC;


}
