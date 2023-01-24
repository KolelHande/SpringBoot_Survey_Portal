package com.company.portal.demo.service.impl;

import com.company.portal.demo.entity.QuestionType;
import com.company.portal.demo.payload.dto.QuestionTypeDto;
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
    public QuestionType createQuestionType(QuestionTypeDto questionTypeDto) {
        QuestionType questionType = new QuestionType();
        questionType.setType(questionTypeDto.getType());
        return questionTypeRepository.save(questionType);
    }

    @Override
    public void deleteQuestionType(Long id) {
        questionTypeRepository.deleteById(id);
    }
}
