package com.company.portal.demo.payload.request.survey;

import com.company.portal.demo.entity.Answer;
import com.company.portal.demo.entity.QuestionOption;
import lombok.Data;

import java.util.List;

@Data
public class CreateAnswerRequest {

    private Answer answer;

    private List<QuestionOption> answerOptions;
}
