package com.company.portal.demo.service;

import com.company.portal.demo.entity.QuestionType;
import com.company.portal.demo.payload.request.question.CreateQuestionTypeRequest;

import java.util.List;

public interface QuestionTypeService {
    List<QuestionType> getQuestionTypes();

    QuestionType createQuestionType(CreateQuestionTypeRequest questionType);

    void deleteQuestionType(Long id);

    QuestionType getQuestionTypeById(Long questionTypeId);
}
