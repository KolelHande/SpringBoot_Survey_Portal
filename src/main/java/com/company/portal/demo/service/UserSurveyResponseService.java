package com.company.portal.demo.service;

import com.company.portal.demo.entity.UserSurveyResult;
import com.company.portal.demo.payload.dto.UserSurveyResponseDto;

import java.util.List;

public interface UserSurveyResponseService {

    UserSurveyResult createSurveyResponse(UserSurveyResponseDto surveyResponseDto);

    UserSurveyResult getSurveyResponseById(Long id);

    List<UserSurveyResult> getAllSurveyResponses();

    UserSurveyResult getSurveyResponseByUserIdAndSurveyId(Long userId, Long surveyId);
}
