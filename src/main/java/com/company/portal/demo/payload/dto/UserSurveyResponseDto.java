package com.company.portal.demo.payload.dto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSurveyResponseDto {
    private Long surveyId;
    private Long userId;
    private List<AnswerDto> answers;
}
