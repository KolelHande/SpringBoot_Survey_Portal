package com.company.portal.demo.service;

import com.company.portal.demo.entity.QuestionOption;

import java.util.Set;

public interface QuestionOptionService {
    QuestionOption getOptionById(Long id);

    void deleteOption(Long id);

    QuestionOption createOption(QuestionOption option);

    Set<QuestionOption> getAnswerOptionsByIds(Set<Long> answerOptionIds);
}
