package com.company.portal.demo.service.impl;

import com.company.portal.demo.entity.*;
import com.company.portal.demo.payload.dto.AnswerDto;
import com.company.portal.demo.payload.request.survey.UserSurveyResultRequest;
import com.company.portal.demo.repository.UserSurveyResultRepository;
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
public class UserSurveyResultServiceImpl implements UserSurveyResultService {

    private final UserSurveyResultRepository userSurveyResultRepository;

    private final SurveyService surveyService;

    private final UserService userService;

    private final QuestionOptionService answerOptionService;

    private final QuestionService questionService;

    @Override
    @Transactional
    public UserSurveyResult createUserSurveyResult(Long surveyId, UserSurveyResultRequest userSurveyResultRequest) {
        User user = userService.getUserById(userSurveyResultRequest.getUserId());
        Survey survey = surveyService.getSurveyById(surveyId);

        UserSurveyResult surveyResult = new UserSurveyResult();
        surveyResult.setUpdatedResponseCount(1);
        surveyResult.setResponseDate(new Date());
        surveyResult.setSurvey(survey);
        surveyResult.setUser(user);

        Set<Answer> answers = new HashSet<>();

        userSurveyResultRequest.getAnswers().forEach(answerDto ->
            setAnswer(answers, answerDto)
        );
        surveyResult.setAnswers(answers);

        UserSurveyResult savedSurveyResult = userSurveyResultRepository.save(surveyResult);

        return savedSurveyResult;
    }

    private void setAnswer( Set<Answer> answers, AnswerDto answerDto) {
        Answer answer = new Answer();
        answer.setAnswerText(answerDto.getAnswerText());

        Question question = questionService.getQuestionById(answerDto.getQuestionId());
        answer.setQuestion(question);
        if (answerDto.getAnswerOptionIds() != null) {
            Set<QuestionOption> answerOptions = answerOptionService.getAnswerOptionsByIds(answerDto.getAnswerOptionIds());
            answer.setAnswerOptions(answerOptions);
        }
        answers.add(answer);
    }

    @Override
    public List<UserSurveyResult> getAllSurveyResult() {
        return userSurveyResultRepository.findAll();
    }

    @Override
    public UserSurveyResult getSurveyResultByUserIdAndSurveyId(Long surveyId, Long userId) {

        UserSurveyResult result = userSurveyResultRepository.findBySurveyIdAndUserId(surveyId, userId)
                .orElseThrow(() -> new EntityNotFoundException("survey reponse not found"));

        return result;
    }

    @Override
    public UserSurveyResult updateUserSurveyResult(Long surveyId, UserSurveyResultRequest userSurveyResultRequest) {

        User user = userService.getUserById(userSurveyResultRequest.getUserId());
        Survey survey = surveyService.getSurveyById(surveyId);
        UserSurveyResult userSurveyResult = getSurveyResultByUserIdAndSurveyId(surveyId, user.getId());

        if(userSurveyResult.getUpdatedResponseCount() == survey.getMaxResponse()){
            throw new RuntimeException("You have reached the maximum number of responses");
        }

        userSurveyResult.setUpdatedResponseCount(userSurveyResult.getUpdatedResponseCount()+1);
        userSurveyResult.setResponseDate(new Date());
        userSurveyResult.setSurvey(survey);
        userSurveyResult.setUser(user);

        userSurveyResult.getAnswers().forEach(answer -> updateAnswer(userSurveyResultRequest, answer) );

        UserSurveyResult savedSurveyResult = userSurveyResultRepository.save(userSurveyResult);

        return savedSurveyResult;
    }

    private void updateAnswer(UserSurveyResultRequest request, Answer answer) {
        request.getAnswers().forEach(answerDto -> {
            if(answer.getQuestion().getId().equals(answerDto.getQuestionId())){
                answer.setAnswerText(answerDto.getAnswerText());
                Question question = questionService.getQuestionById(answerDto.getQuestionId());
                answer.setQuestion(question);

                if(answerDto.getAnswerOptionIds() != null){
                    Set<QuestionOption> answerOptions = answerOptionService.getAnswerOptionsByIds(answerDto.getAnswerOptionIds());
                    answer.setAnswerOptions(answerOptions);
                }
            }
        });
    }


}
