package com.company.portal.demo.service.impl;

import com.company.portal.demo.entity.Question;
import com.company.portal.demo.entity.Survey;
import com.company.portal.demo.repository.QuestionOptionRepository;
import com.company.portal.demo.repository.QuestionTypeRepository;
import com.company.portal.demo.repository.SurveyRepository;
import com.company.portal.demo.service.QuestionService;
import com.company.portal.demo.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;

    private final QuestionService questionService;
    private final QuestionTypeRepository questionTypeRepository;
    private final QuestionOptionRepository questionOptionRepository;

    @Override
    public Survey createSurvey(Survey survey) {
        Survey savedSurvey = surveyRepository.save(survey);
        for (Question question : survey.getQuestions()) {
            question.setSurvey(savedSurvey);
            questionService.createQuestion(question);
        }
        return savedSurvey;
    }

    @Override
    public Survey getSurveyById(Long id) {
        return surveyRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteSurvey(Long id) {
        surveyRepository.deleteById(id);
    }

}