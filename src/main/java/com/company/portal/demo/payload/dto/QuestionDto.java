package com.company.portal.demo.payload.dto;

import com.company.portal.demo.payload.model.QuestionTypeModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class QuestionDto {
    private Long id;
    private String text;
    private int orderNumber;
    private boolean optional;
    private QuestionTypeModel questionType;
    private List<QuestionOptionDto> questionOptions;

}
