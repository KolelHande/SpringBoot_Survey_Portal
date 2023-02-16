package com.company.portal.demo.mapper.survey;

import com.company.portal.demo.entity.Survey;
import com.company.portal.demo.payload.request.survey.CreateSurveyRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface SurveyMapper {

    Survey createSurveyRequestToSurvey(CreateSurveyRequest request);
}
