package com.company.portal.demo.service.impl;

import com.company.portal.demo.entity.*;
import com.company.portal.demo.repository.*;
import com.company.portal.demo.service.SurveyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;
    private final QuestionTypeRepository questionTypeRepository;
    private final QuestionOptionRepository questionOptionRepository;
    private final AnswerRepository answerRepository;

    public SurveyServiceImpl(SurveyRepository surveyRepository,
                             QuestionRepository questionRepository,
                             QuestionTypeRepository questionTypeRepository,
                             QuestionOptionRepository questionOptionRepository,
                             AnswerRepository answerRepository) {
        this.surveyRepository = surveyRepository;
        this.questionRepository = questionRepository;
        this.questionTypeRepository = questionTypeRepository;
        this.questionOptionRepository = questionOptionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    @Override
    public void deleteSurvey(Long id) {
        surveyRepository.deleteById(id);
    }

    /*
    @Override
    public Survey createSurvey(SurveyDto surveyDto) {
        // Create new survey
        Survey survey = new Survey();
        survey.setName(surveyDto.getName());
        survey.setDescription(surveyDto.getDescription());
        survey.setStartDate(surveyDto.getStartDate());
        survey.setEndDate(surveyDto.getEndDate());
        survey.setMinResponse(surveyDto.getMinResponse());
        survey.setMaxResponse(surveyDto.getMaxResponse());

        List<Question> questions = new ArrayList<>();
        for (Question questionDto : surveyDto.getQuestions()) {
            // Create questions
            Question question = new Question();
            question.setText(questionDto.getText());
            question.setOrderNumber(questionDto.getOrderNumber());
            question.setOptional(questionDto.getOptional());
            question.setSurvey(survey);

            // Set question type
            QuestionType questionType = questionTypeRepository.findById(questionDto.getQuestionType().getId()).orElse(null);
            question.setQuestionType(questionType);


            questions.add(question);
        }
        survey.setQuestions((Set<Question>) questions);

        return surveyRepository.save(survey);
    }
    */


}