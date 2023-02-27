package com.company.portal.demo.mapper.questionoption;

import com.company.portal.demo.entity.QuestionOption;
import com.company.portal.demo.payload.request.questionOption.CreateQuestionOptionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface QuestionOptionMapper {

    QuestionOption createQuestionOptionRequestToQuestionOption(CreateQuestionOptionRequest request);
}
