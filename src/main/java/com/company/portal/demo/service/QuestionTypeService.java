package com.company.portal.demo.service;

import com.company.portal.demo.entity.QuestionType;

import java.util.List;

public interface QuestionTypeService {
    List<QuestionType> getQuestionTypes();

    QuestionType createQuestionType(QuestionType questionType);

    void deleteQuestionType(Long id);

    QuestionType getQuestionTypeById(Long questionTypeId);
}
