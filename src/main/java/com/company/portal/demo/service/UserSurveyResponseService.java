package com.company.portal.demo.service;

import com.company.portal.demo.entity.UserSurveyResponse;
import com.company.portal.demo.payload.dto.UserSurveyResponseDto;

import java.util.List;

public interface UserSurveyResponseService {

    UserSurveyResponse createSurveyResponse(UserSurveyResponseDto surveyResponseDto);

    UserSurveyResponse getSurveyResponseById(Long id);

    List<UserSurveyResponse> getAllSurveyResponses();

    UserSurveyResponse getSurveyResponseByUserIdAndSurveyId(Long userId, Long surveyId);
}
