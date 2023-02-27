package com.company.portal.demo.mapper.question;

import com.company.portal.demo.entity.Question;
import com.company.portal.demo.entity.QuestionType;
import com.company.portal.demo.payload.dto.QuestionDto;
import com.company.portal.demo.payload.request.question.CreateQuestionRequest;
import com.company.portal.demo.payload.request.question.CreateQuestionTypeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface QuestionMapper {

    Question createQuestionRequestToQuestion(CreateQuestionRequest request);
    List<QuestionDto> questionListToQuestionDtoList(List<Question> questions);
    QuestionType questionTypeRequestToQuestionType(CreateQuestionTypeRequest request);
}
