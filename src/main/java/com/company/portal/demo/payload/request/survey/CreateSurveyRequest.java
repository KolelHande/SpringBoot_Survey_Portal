package com.company.portal.demo.payload.request.survey;

import com.company.portal.demo.payload.request.question.CreateQuestionRequest;
import com.company.portal.demo.util.GeneralDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Data
public class CreateSurveyRequest {

    @NotEmpty(message = "{survey.name.not.empty}")
    private String name;

    @NotEmpty(message = "{description.not.empty}")
    @Size( max = 250 , message = "{max.character.description} 250.")
    private String description;

    @JsonDeserialize(using = GeneralDateDeserializer.class)
    private Date createDate;

    @NotNull(message = "{publish.date.empty}")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Future(message = "{publish.date.future}")
    @JsonDeserialize(using = GeneralDateDeserializer.class)
    private Date publishDate;

    @NotNull(message = "{end.date.empty}")
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    @Future(message = "{end.date.future}")
    @JsonDeserialize(using = GeneralDateDeserializer.class)
    private Date endDate;

    private Set<CreateQuestionRequest> questions;
}
