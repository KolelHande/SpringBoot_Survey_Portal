package com.company.portal.demo.service;

import com.company.portal.demo.entity.Question;

public interface QuestionService {
    Question createQuestion(Question question);

    Question getQuestionById(Long id);

    void deleteQuestion(Long id);
}
