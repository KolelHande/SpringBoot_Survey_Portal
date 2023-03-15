package com.company.portal.demo.payload.request.question;

import com.company.portal.demo.enums.QuestionTypeEnum;
import com.company.portal.demo.util.validation.ValidQuestionType;
import lombok.Data;

@Data
public class CreateQuestionTypeRequest {

    @ValidQuestionType
    private QuestionTypeEnum type;
}
