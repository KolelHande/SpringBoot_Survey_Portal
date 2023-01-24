package com.company.portal.demo.payload.dto;

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
    private String text;
    private int orderNumber;
    private boolean optional;
    private Long questionTypeId;
    private List<QuestionOptionDto> options;

}
