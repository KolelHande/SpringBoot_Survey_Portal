package com.company.portal.demo.mapper.surveyresult;

import com.company.portal.demo.entity.UserSurveyResult;
import com.company.portal.demo.payload.dto.UserSurveyResultAnswerDto;
import com.company.portal.demo.payload.dto.UserSurveyResultDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface SurveyResultMapper {

    UserSurveyResultAnswerDto createUserSurveyResultToUserSurveyResultAnswerDto(UserSurveyResult userSurveyResult);
    List<UserSurveyResultDto> userSurveyListToUserSurveyDtoList (List<UserSurveyResult> userSurveyResults);
}
