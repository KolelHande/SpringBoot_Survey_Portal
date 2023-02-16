package com.company.portal.demo.payload.request.survey;

import com.company.portal.demo.payload.dto.AnswerDto;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSurveyResultRequest {
    private Long userId;
    private List<AnswerDto> answers;
}
