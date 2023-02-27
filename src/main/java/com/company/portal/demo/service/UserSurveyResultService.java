package com.company.portal.demo.service;

import com.company.portal.demo.entity.UserSurveyResult;
import com.company.portal.demo.payload.dto.PaginatedUserSurveyResultDto;
import com.company.portal.demo.payload.dto.UserDto;
import com.company.portal.demo.payload.dto.UserSurveyResultAnswerDto;
import com.company.portal.demo.payload.request.survey.UserSurveyResultRequest;

import java.util.List;

public interface UserSurveyResultService {

    UserSurveyResult createUserSurveyResult(Long surveyId, UserSurveyResultRequest surveyResponseDto);

    List<UserSurveyResult> getAllSurveyResult();

    UserSurveyResult getSurveyResultByUserIdAndSurveyId(Long userId, Long surveyId);

    UserSurveyResult updateUserSurveyResult(Long surveyId, UserSurveyResultRequest surveyResponseDto);

    UserSurveyResultAnswerDto getUserSurveyResultAnswerByUserIdAndSurveyId(Long surveyId, Long userId);

    List<UserDto> getSurveyUsersBySurveyId(Long surveyId);

    PaginatedUserSurveyResultDto getPaginatedUserSurveyResults(int pageNo, int pageSize, String sortBy, String sortDir);
}
