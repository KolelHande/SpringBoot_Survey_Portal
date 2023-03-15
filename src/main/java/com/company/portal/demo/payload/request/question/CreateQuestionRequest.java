package com.company.portal.demo.payload.request.question;

import com.company.portal.demo.payload.model.QuestionTypeModel;
import com.company.portal.demo.payload.request.questionOption.CreateQuestionOptionRequest;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CreateQuestionRequest {

    @NotEmpty(message = "{question.text.not.empty}")
    private String text;

    @Size(min = 1, message = "{question.text.not.empty}")
    private Integer orderNumber;

    private Boolean optional;

    @NotNull(message = "{question.type.not.null}")
    private QuestionTypeModel questionType;

    private List<CreateQuestionOptionRequest> questionOptions;
}
