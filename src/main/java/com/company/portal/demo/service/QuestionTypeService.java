package com.company.portal.demo.service;

import com.company.portal.demo.entity.QuestionType;
import com.company.portal.demo.payload.dto.QuestionTypeDto;

import java.util.List;

public interface QuestionTypeService {
    List<QuestionType> getQuestionTypes();

    QuestionType createQuestionType(QuestionTypeDto questionTypeDto);

    void deleteQuestionType(Long id);
}
