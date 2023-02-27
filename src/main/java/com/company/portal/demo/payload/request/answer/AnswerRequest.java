package com.company.portal.demo.payload.request.answer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnswerRequest {
    private String answerText;
    private Long questionId;
    private Set<Long> answerOptionIds;
}
