package com.company.portal.demo.service.impl;

import com.company.portal.demo.entity.*;
import com.company.portal.demo.mapper.answer.AnswerMapper;
import com.company.portal.demo.mapper.question.QuestionMapper;
import com.company.portal.demo.mapper.surveyresult.SurveyResultMapper;
import com.company.portal.demo.mapper.user.UserMapper;
import com.company.portal.demo.payload.dto.*;
import com.company.portal.demo.payload.request.answer.AnswerRequest;
import com.company.portal.demo.payload.request.survey.UserSurveyResultRequest;
import com.company.portal.demo.repository.UserSurveyResultRepository;
import com.company.portal.demo.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    private final SurveyResultMapper surveyResultMapper;

    private final UserMapper userMapper;

    private final QuestionMapper questionMapper;

    private final AnswerMapper answerMapper;

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

        userSurveyResultRequest.getAnswers().forEach(answerRequest ->
            setAnswer(answers, answerRequest)
        );
        surveyResult.setAnswers(answers);

        UserSurveyResult savedSurveyResult = userSurveyResultRepository.save(surveyResult);

        return savedSurveyResult;
    }

    private void setAnswer( Set<Answer> answers, AnswerRequest answerRequest) {
        Answer answer = new Answer();
        answer.setAnswerText(answerRequest.getAnswerText());

        Question question = questionService.getQuestionById(answerRequest.getQuestionId());
        answer.setQuestion(question);
        if (answerRequest.getAnswerOptionIds() != null) {
            Set<QuestionOption> answerOptions = answerOptionService.getAnswerOptionsByIds(answerRequest.getAnswerOptionIds());
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

        userSurveyResult.setUpdatedResponseCount(userSurveyResult.getUpdatedResponseCount()+1);
        userSurveyResult.setResponseDate(new Date());
        userSurveyResult.setSurvey(survey);
        userSurveyResult.setUser(user);

        userSurveyResult.getAnswers().forEach(answer -> updateAnswer(userSurveyResultRequest, answer) );

        UserSurveyResult savedSurveyResult = userSurveyResultRepository.save(userSurveyResult);

        return savedSurveyResult;
    }

    private void updateAnswer(UserSurveyResultRequest request, Answer answer) {
        request.getAnswers().forEach(answerRequest -> {
            if(answer.getQuestion().getId().equals(answerRequest.getQuestionId())){
                answer.setAnswerText(answerRequest.getAnswerText());
                Question question = questionService.getQuestionById(answerRequest.getQuestionId());
                answer.setQuestion(question);

                if(answerRequest.getAnswerOptionIds() != null){
                    Set<QuestionOption> answerOptions = answerOptionService.getAnswerOptionsByIds(answerRequest.getAnswerOptionIds());
                    answer.setAnswerOptions(answerOptions);
                }
            }
        });
    }

    @Override
    public UserSurveyResultAnswerDto getUserSurveyResultAnswerByUserIdAndSurveyId(Long surveyId, Long userId) {

        List<QuestionDto> questions = questionMapper.questionListToQuestionDtoList(surveyService.getQuestionsBySurveyId(surveyId));
        List<AnswerDto> answers = answerMapper.answerListToAnswerDtoList(userSurveyResultRepository.findAnswerBySurveyIdAndUserId(surveyId,userId));

        UserSurveyResultAnswerDto userSurveyResultAnswerDto = UserSurveyResultAnswerDto.builder()
                .answers(answers)
                .questions(questions)
                .build();

        return userSurveyResultAnswerDto;
    }

    @Override
    public PaginatedUserSurveyResultDto getPaginatedUserSurveyResults(int pageNo, int pageSize, String sortBy, String sortDir){
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<UserSurveyResult> surveyResults = userSurveyResultRepository.findAll(pageable);

        List<UserSurveyResultDto> content = surveyResultMapper.userSurveyListToUserSurveyDtoList(surveyResults.getContent());

        return PaginatedUserSurveyResultDto.builder()
                .pageable(pageable)
                .userSurveyResults(content)
                .count(surveyResults.getTotalElements())
                .page(surveyResults.getPageable().getPageNumber())
                .size(surveyResults.getPageable().getPageSize())
                .totalPageNumber(surveyResults.getTotalPages())
                .build();
    }

    public List<UserDto> getSurveyUsersBySurveyId(Long surveyId) {
        List<User> users = userSurveyResultRepository.findUserBySurveyId(surveyId);

        return userMapper.usersToUserDtoList(users);
    }


}
