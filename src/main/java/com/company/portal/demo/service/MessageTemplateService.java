package com.company.portal.demo.service;

import com.company.portal.demo.payload.dto.MessageTemplateDto;

public interface MessageTemplateService {
    MessageTemplateDto getMessageByOperationId(Long operationId);
}
