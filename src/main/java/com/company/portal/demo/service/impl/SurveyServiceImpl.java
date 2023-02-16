package com.company.portal.demo.service.impl;

import com.company.portal.demo.entity.Survey;
import com.company.portal.demo.mapper.survey.SurveyMapper;
import com.company.portal.demo.payload.request.survey.CreateSurveyRequest;
import com.company.portal.demo.payload.request.survey.UpdateSubmittedSurveyRequest;
import com.company.portal.demo.repository.SurveyRepository;
import com.company.portal.demo.service.QuestionService;
import com.company.portal.demo.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;

    private final QuestionService questionService;

    private final SurveyMapper surveyMapper;

    @Override
    @Transactional
    public Survey createSurvey(CreateSurveyRequest surveyRequest) {

        Survey survey = surveyMapper.createSurveyRequestToSurvey(surveyRequest);

/*        Set<Question> questions = new HashSet<>();

        for (Question question : survey.getQuestions()) {

            List<QuestionOption> questionOptions = new ArrayList<>();

            question.getQuestionOptions().forEach(questionOption -> {
                QuestionOption questionOptionEntity = QuestionOption.builder()
                        .orderNumber(questionOption.getOrderNumber())
                        .value(questionOption.getValue())
                        .build();
                questionOptions.add(questionOptionEntity);
            });

            Question questionEntity = Question.builder()
                    .questionType(question.getQuestionType())
                    .text(question.getText())
                    .optional(question.getOptional())
                    .orderNumber(question.getOrderNumber())
                    .questionOptions(questionOptions)
                    .build();

            questions.add(questionEntity);
        }

        Survey surveyEntity = Survey.builder()
                .startDate(survey.getStartDate())
                .description(survey.getDescription())
                .endDate(survey.getEndDate())
                .maxResponse(survey.getMaxResponse())
                .minResponse(survey.getMinResponse())
                .name(survey.getName())
                .questions(questions)
                .build();*/

        return surveyRepository.save(survey);
    }

    @Override
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @Override
    public Survey getSurveyById(Long id) {

        return surveyRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteSurvey(Long id) {
        surveyRepository.deleteById(id);
    }

    @Override
    public Survey updateSubmittedSurvey(Long surveyId, UpdateSubmittedSurveyRequest request) {
        Survey survey = surveyRepository.findById(surveyId).orElseThrow(() -> new EntityNotFoundException("Survey not found with id: " + surveyId));
        survey.setEndDate(request.getEndDate());
        return surveyRepository.save(survey);
    }


}