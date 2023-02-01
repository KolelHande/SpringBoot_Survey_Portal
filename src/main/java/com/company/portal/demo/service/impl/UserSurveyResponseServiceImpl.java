package com.company.portal.demo.service.impl;

import com.company.portal.demo.entity.*;
import com.company.portal.demo.payload.dto.AnswerDto;
import com.company.portal.demo.payload.dto.UserSurveyResponseDto;
import com.company.portal.demo.repository.UserSurveyResponseRepository;
import com.company.portal.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserSurveyResponseServiceImpl implements UserSurveyResponseService {

    private final UserSurveyResponseRepository surveyResponseRepository;

    private final SurveyService surveyService;

    private final UserService userService;

    private final QuestionOptionService answerOptionService;

    private final QuestionService questionService;

    @Override
    public UserSurveyResponse createSurveyResponse(UserSurveyResponseDto surveyResponseDto) {
        User user = userService.getUserById(surveyResponseDto.getUserId());
        Survey survey = surveyService.getSurveyById(surveyResponseDto.getSurveyId());

        UserSurveyResponse surveyResponse = new UserSurveyResponse();
        surveyResponse.setUpdatedResponseCount(1);
        surveyResponse.setResponseDate(new Date());
        surveyResponse.setSurvey(survey);
        surveyResponse.setUser(user);

        Set<Answer> answers = new HashSet<>();

        surveyResponseDto.getAnswers().forEach(answerDto ->
            setAnswer(surveyResponse, answers, answerDto)
        );
        surveyResponse.setAnswers(answers);

        UserSurveyResponse savedResponse = surveyResponseRepository.save(surveyResponse);

        return savedResponse;
    }

    private void setAnswer(UserSurveyResponse surveyResponse, Set<Answer> answers, AnswerDto answerDto) {
        Answer answer = new Answer();
        answer.setAnswerText(answerDto.getAnswerText());

        Question question = questionService.getQuestionById(answerDto.getQuestionId());
        answer.setQuestion(question);
        if (answerDto.getAnswerOptionIds() != null) {
            Set<QuestionOption> answerOptions = answerOptionService.getAnswerOptionsByIds(answerDto.getAnswerOptionIds());
            answer.setAnswerOptions(answerOptions);
        }
        answer.setUserSurveyResponse(surveyResponse);
        answers.add(answer);
    }

    @Override
    public UserSurveyResponse getSurveyResponseById(Long id) {
        return surveyResponseRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserSurveyResponse> getAllSurveyResponses() {
        return surveyResponseRepository.findAll();
    }

    @Override
    public UserSurveyResponse getSurveyResponseByUserIdAndSurveyId(Long surveyId, Long userId) {

        UserSurveyResponse response = surveyResponseRepository.findBySurveyIdAndUserId(surveyId, userId)
                .orElseThrow(() -> new EntityNotFoundException("survey reponse not found"));

        return response;
    }
}
