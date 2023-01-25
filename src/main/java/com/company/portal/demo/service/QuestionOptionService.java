package com.company.portal.demo.service;

import com.company.portal.demo.entity.QuestionOption;

public interface QuestionOptionService {
    QuestionOption getOptionById(Long id);

    void deleteOption(Long id);

    QuestionOption createOption(QuestionOption option);
}
