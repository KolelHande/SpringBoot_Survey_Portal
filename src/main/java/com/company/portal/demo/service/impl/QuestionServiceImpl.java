package com.company.portal.demo.service.impl;

import com.company.portal.demo.entity.Question;
import com.company.portal.demo.entity.QuestionOption;
import com.company.portal.demo.entity.QuestionType;
import com.company.portal.demo.repository.QuestionRepository;
import com.company.portal.demo.service.QuestionOptionService;
import com.company.portal.demo.service.QuestionService;
import com.company.portal.demo.service.QuestionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    private final QuestionOptionService questionOptionService;

    private final QuestionTypeService questionTypeService;

    @Override
    public Question createQuestion(Question question) {
        QuestionType questionType = questionTypeService.getQuestionTypeById(question.getQuestionType().getId());
        question.setQuestionType(questionType);

        Question savedQuestion = questionRepository.save(question);

        if (question.getQuestionOptions() != null) {
            for (QuestionOption option : question.getQuestionOptions()) {
                option.setQuestion(savedQuestion);
                questionOptionService.createOption(option);
            }
        }
        return savedQuestion;
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }
}
