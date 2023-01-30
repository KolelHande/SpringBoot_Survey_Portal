package com.company.portal.demo.payload.request.survey;

import com.company.portal.demo.util.GeneralDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.Date;

@Data
public class UpdateSubmittedSurveyRequest {

    @JsonDeserialize(using = GeneralDateDeserializer.class)
    private Date endDate;
}
