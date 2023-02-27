package com.company.portal.demo.mapper.survey;

import com.company.portal.demo.entity.Survey;
import com.company.portal.demo.payload.dto.SurveyDto;
import com.company.portal.demo.payload.request.survey.CreateSurveyRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface SurveyMapper {

    Survey createSurveyRequestToSurvey(CreateSurveyRequest request);

    SurveyDto createSurveyToSurveyDto(Survey request);

    List<SurveyDto> surveyListToSurveyDtoList(List<Survey> request);
}
