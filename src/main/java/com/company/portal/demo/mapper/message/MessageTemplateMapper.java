package com.company.portal.demo.mapper.message;

import com.company.portal.demo.entity.MessageTemplate;
import com.company.portal.demo.payload.dto.MessageTemplateDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface MessageTemplateMapper {

    MessageTemplateDto messageTemplateToMessageTemplateDto(MessageTemplate messageTemplate);

}
