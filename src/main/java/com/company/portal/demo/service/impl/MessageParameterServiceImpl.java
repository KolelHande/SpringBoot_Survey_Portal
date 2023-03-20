package com.company.portal.demo.service.impl;

import com.company.portal.demo.entity.MessageParameter;
import com.company.portal.demo.enums.MessageParameterEnum;
import com.company.portal.demo.repository.MessageParameterRepository;
import com.company.portal.demo.service.MessageParameterService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MessageParameterServiceImpl implements MessageParameterService {
    private final MessageParameterRepository messageParameterRepository;

    @Override
    @Cacheable(value = "getAllMessageParameter")
    public Map<MessageParameterEnum,String> getAllMessageParameter(){
        return messageParameterRepository.findAll().stream()
                .collect(Collectors.toMap(MessageParameter::getMessageKey, MessageParameter::getMessageValue));
    }
}