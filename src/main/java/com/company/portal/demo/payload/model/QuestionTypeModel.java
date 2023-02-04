package com.company.portal.demo.payload.model;

import com.company.portal.demo.enums.QuestionTypeEnum;
import lombok.Data;

@Data
public class QuestionTypeModel {

    private Long id;
    private QuestionTypeEnum type;
}
