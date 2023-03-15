package com.company.portal.demo.payload.request.survey;

import com.company.portal.demo.util.GeneralDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UpdateSubmittedSurveyRequest {

    @NotNull(message = "{end.date.empty}")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Future(message = "{end.date.future}")
    @JsonDeserialize(using = GeneralDateDeserializer.class)
    private Date endDate;
}
