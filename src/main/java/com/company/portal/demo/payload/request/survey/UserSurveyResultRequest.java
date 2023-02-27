package com.company.portal.demo.payload.request.survey;

import com.company.portal.demo.payload.request.answer.AnswerRequest;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSurveyResultRequest {
    private Long userId;
    private List<AnswerRequest> answers;
}
