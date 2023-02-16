package com.company.portal.demo.payload.request.survey;

import com.company.portal.demo.payload.request.question.CreateQuestionRequest;
import com.company.portal.demo.util.GeneralDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class CreateSurveyRequest {

    private String name;
    private String description;
    @JsonDeserialize(using = GeneralDateDeserializer.class)
    private Date startDate;
    @JsonDeserialize(using = GeneralDateDeserializer.class)
    private Date endDate;
    private Integer minResponse;
    private Integer maxResponse;
    private Set<CreateQuestionRequest> questions;
}
