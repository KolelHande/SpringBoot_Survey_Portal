package com.company.portal.demo.payload.request.survey;

import com.company.portal.demo.payload.request.answer.AnswerRequest;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSurveyResultRequest {

    private Long userId;

    @NotEmpty(message = "{answers.not.empty}")
    private List<AnswerRequest> answers;
}
