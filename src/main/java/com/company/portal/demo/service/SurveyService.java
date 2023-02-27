package com.company.portal.demo.service;

import com.company.portal.demo.entity.Question;
import com.company.portal.demo.entity.Survey;
import com.company.portal.demo.payload.dto.PaginatedSurveyDto;
import com.company.portal.demo.payload.request.survey.CreateSurveyRequest;
import com.company.portal.demo.payload.request.survey.UpdateSubmittedSurveyRequest;

import java.util.List;

public interface SurveyService {

    Survey createSurvey(CreateSurveyRequest request);
    
    Survey getSurveyById(Long id);

    void deleteSurvey(Long id);

    Survey updateSubmittedSurvey(Long surveyId, UpdateSubmittedSurveyRequest request);

    List<Question> getQuestionsBySurveyId(Long surveyId);

    PaginatedSurveyDto getPaginatedSurveys(int pageNo, int pageSize, String sortBy, String sortDir);
}
