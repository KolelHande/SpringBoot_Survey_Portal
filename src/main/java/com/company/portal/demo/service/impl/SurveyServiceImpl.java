package com.company.portal.demo.service.impl;

import com.company.portal.demo.entity.Question;
import com.company.portal.demo.entity.Survey;
import com.company.portal.demo.payload.request.survey.UpdateSubmittedSurveyRequest;
import com.company.portal.demo.repository.SurveyRepository;
import com.company.portal.demo.service.QuestionService;
import com.company.portal.demo.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;

    private final QuestionService questionService;

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
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @Override
    public Survey getSurveyById(Long id) {

        return surveyRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteSurvey(Long id) {
        surveyRepository.deleteById(id);
    }

    @Override
    public Survey updateSubmittedSurvey(Long surveyId, UpdateSubmittedSurveyRequest request){
        Survey survey= surveyRepository.findById(surveyId).orElseThrow(() -> new EntityNotFoundException("Survey not found with id: " + surveyId) );
        survey.setEndDate(request.getEndDate());
        return surveyRepository.save(survey);
    }

}