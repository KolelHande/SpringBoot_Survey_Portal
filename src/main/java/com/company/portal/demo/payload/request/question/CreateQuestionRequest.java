package com.company.portal.demo.payload.request.question;

import com.company.portal.demo.payload.model.QuestionTypeModel;
import com.company.portal.demo.payload.request.questionoption.CreateQuestionOptionRequest;
import lombok.Data;

import java.util.List;

@Data
public class CreateQuestionRequest {

    private String text;
    private Integer orderNumber;
    private Boolean optional;
    private QuestionTypeModel questionType;
    private List<CreateQuestionOptionRequest> questionOptions;
}
