package com.company.portal.demo.service;

import com.company.portal.demo.entity.Answer;
import com.company.portal.demo.payload.dto.AnswerDto;

public interface AnswerService {
    Answer getAnswerById(Long id);

    Answer createAnswer(AnswerDto answer);
}
