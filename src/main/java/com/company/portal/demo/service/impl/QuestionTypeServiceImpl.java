package com.company.portal.demo.service.impl;

import com.company.portal.demo.entity.QuestionType;
import com.company.portal.demo.repository.QuestionTypeRepository;
import com.company.portal.demo.service.QuestionTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionTypeServiceImpl implements QuestionTypeService {

    private final QuestionTypeRepository questionTypeRepository;

    public QuestionTypeServiceImpl(QuestionTypeRepository questionTypeRepository) {
        this.questionTypeRepository = questionTypeRepository;
    }

    @Override
    public List<QuestionType> getQuestionTypes() {
        return questionTypeRepository.findAll();
    }

    @Override
    public QuestionType createQuestionType(QuestionType questionType) {
        questionType.setType(questionType.getType());
        return questionTypeRepository.save(questionType);
    }

    @Override
    public void deleteQuestionType(Long id) {
        questionTypeRepository.deleteById(id);
    }

    @Override
    public QuestionType getQuestionTypeById(Long questionTypeId) {
        return questionTypeRepository.findById(questionTypeId).orElse(null);
    }
}
