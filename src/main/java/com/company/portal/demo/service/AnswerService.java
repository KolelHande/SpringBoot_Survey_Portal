package com.company.portal.demo.service;

import com.company.portal.demo.entity.Answer;

import java.util.Set;

public interface AnswerService {
    Answer getAnswerById(Long id);

    Set<Answer> getAnswerByIds(Set<Long> answerIds);
}
