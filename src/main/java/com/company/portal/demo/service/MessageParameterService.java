package com.company.portal.demo.service;

import com.company.portal.demo.enums.MessageParameterEnum;
import org.springframework.cache.annotation.Cacheable;

import java.util.Map;

public interface MessageParameterService {
    @Cacheable(value = "getAllMessageParameter")
    Map<MessageParameterEnum,String> getAllMessageParameter();
}
