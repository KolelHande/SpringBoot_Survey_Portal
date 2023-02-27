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
public class UserSurveyResultAnswerDto {
    private List<QuestionDto> questions;
    private List<AnswerDto> answers;
}

