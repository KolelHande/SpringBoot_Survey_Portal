package com.company.portal.demo.service.impl;

import com.company.portal.demo.entity.Answer;
import com.company.portal.demo.entity.Question;
import com.company.portal.demo.entity.QuestionOption;
import com.company.portal.demo.payload.dto.AnswerDto;
import com.company.portal.demo.repository.AnswerRepository;
import com.company.portal.demo.service.AnswerService;
import com.company.portal.demo.service.QuestionOptionService;
import com.company.portal.demo.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    private final QuestionService questionService;

    private final QuestionOptionService answerOptionService;

    @Override
    public Answer getAnswerById(Long id){
        return answerRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Answer with id " + id + " not found."));
    }

    @Override
    public Answer createAnswer(AnswerDto answerDto) {

        Question question = questionService.getQuestionById(answerDto.getQuestionId());

        Set<QuestionOption> answerOptions = answerOptionService.getAnswerOptionsByIds(answerDto.getAnswerOptionIds());

        Answer answer = new Answer();
        answer.setAnswerText(answerDto.getAnswerText());
        answer.setQuestion(question);
        answer.setAnswerOptions(answerOptions);

        return answerRepository.save(answer);
    }
}
