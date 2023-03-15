package com.company.portal.demo.service.impl;

import com.company.portal.demo.entity.MessageTemplate;
import com.company.portal.demo.exception.ResourceNotFoundException;
import com.company.portal.demo.mapper.message.MessageTemplateMapper;
import com.company.portal.demo.payload.dto.MessageTemplateDto;
import com.company.portal.demo.repository.MessageTemplateRepository;
import com.company.portal.demo.service.MessageTemplateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageTemplateServiceImpl implements MessageTemplateService {

    private final MessageTemplateRepository messageTemplateRepository;
    private final MessageTemplateMapper messageTemplateMapper;

    @Override
    public MessageTemplateDto getMessageByOperationId(Long operationId){

        MessageTemplate messageTemplate = messageTemplateRepository.findByOperationId(operationId)
                .orElseThrow(() -> new ResourceNotFoundException("Message is not found by operation id."));

        return messageTemplateMapper.messageTemplateToMessageTemplateDto(messageTemplate);

    }
}
