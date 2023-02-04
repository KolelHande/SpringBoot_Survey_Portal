package com.company.portal.demo.service.impl;

import com.company.portal.demo.entity.*;
import com.company.portal.demo.payload.dto.AnswerDto;
import com.company.portal.demo.payload.dto.UserSurveyResponseDto;
import com.company.portal.demo.repository.UserSurveyResponseRepository;
import com.company.portal.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
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
    @Transactional
    public UserSurveyResult createSurveyResponse(UserSurveyResponseDto surveyResponseDto) {
        User user = userService.getUserById(surveyResponseDto.getUserId());
        Survey survey = surveyService.getSurveyById(surveyResponseDto.getSurveyId());

        UserSurveyResult surveyResponse = new UserSurveyResult();
        surveyResponse.setUpdatedResponseCount(1);
        surveyResponse.setResponseDate(new Date());
        surveyResponse.setSurvey(survey);
        surveyResponse.setUser(user);

        Set<Answer> answers = new HashSet<>();

        surveyResponseDto.getAnswers().forEach(answerDto ->
            setAnswer(surveyResponse, answers, answerDto)
        );
        surveyResponse.setAnswers(answers);

        UserSurveyResult savedResponse = surveyResponseRepository.save(surveyResponse);

        return savedResponse;
    }

    private void setAnswer(UserSurveyResult surveyResponse, Set<Answer> answers, AnswerDto answerDto) {
        Answer answer = new Answer();
        answer.setAnswerText(answerDto.getAnswerText());

        Question question = questionService.getQuestionById(answerDto.getQuestionId());
        answer.setQuestion(question);
        if (answerDto.getAnswerOptionIds() != null) {
            Set<QuestionOption> answerOptions = answerOptionService.getAnswerOptionsByIds(answerDto.getAnswerOptionIds());
            answer.setAnswerOptions(answerOptions);
        }
        answer.setUserSurveyResult(surveyResponse);
        answers.add(answer);
    }

    @Override
    public UserSurveyResult getSurveyResponseById(Long id) {
        return surveyResponseRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserSurveyResult> getAllSurveyResponses() {
        return surveyResponseRepository.findAll();
    }

    @Override
    public UserSurveyResult getSurveyResponseByUserIdAndSurveyId(Long surveyId, Long userId) {

        UserSurveyResult response = surveyResponseRepository.findBySurveyIdAndUserId(surveyId, userId)
                .orElseThrow(() -> new EntityNotFoundException("survey reponse not found"));

        return response;
    }
}
