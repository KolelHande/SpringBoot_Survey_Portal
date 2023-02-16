package com.company.portal.demo.mapper.question;

import com.company.portal.demo.entity.Question;
import com.company.portal.demo.payload.request.question.CreateQuestionRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface QuestionMapper {

    Question createQuestionRequestToQuestion(CreateQuestionRequest request);
}
