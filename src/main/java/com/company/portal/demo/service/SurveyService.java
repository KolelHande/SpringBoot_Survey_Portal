package com.company.portal.demo.service;

import com.company.portal.demo.entity.Survey;

import java.util.List;

public interface SurveyService {
    List<Survey> getAllSurveys();

  //  Survey createSurvey(SurveyDto surveyDto);

    void deleteSurvey(Long id);

}
