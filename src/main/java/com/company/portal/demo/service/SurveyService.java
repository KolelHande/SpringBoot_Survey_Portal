package com.company.portal.demo.service;

import com.company.portal.demo.entity.Survey;
import com.company.portal.demo.payload.request.survey.UpdateSubmittedSurveyRequest;

import java.util.List;

public interface SurveyService {

    Survey createSurvey(Survey survey);

    List<Survey> getAllSurveys();

    Survey getSurveyById(Long id);

    void deleteSurvey(Long id);

    Survey updateSubmittedSurvey(Long surveyId, UpdateSubmittedSurveyRequest request);
}
