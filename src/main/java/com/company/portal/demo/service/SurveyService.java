package com.company.portal.demo.service;

import com.company.portal.demo.entity.Survey;

public interface SurveyService {

    Survey createSurvey(Survey survey);
    Survey getSurveyById(Long id);
    void deleteSurvey(Long id);

}
