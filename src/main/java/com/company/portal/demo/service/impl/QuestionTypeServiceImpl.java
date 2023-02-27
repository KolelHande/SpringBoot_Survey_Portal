package com.company.portal.demo.service.impl;

import com.company.portal.demo.entity.QuestionType;
import com.company.portal.demo.mapper.question.QuestionMapper;
import com.company.portal.demo.payload.request.question.CreateQuestionTypeRequest;
import com.company.portal.demo.repository.QuestionTypeRepository;
import com.company.portal.demo.service.QuestionTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionTypeServiceImpl implements QuestionTypeService {

    private final QuestionTypeRepository questionTypeRepository;

    private final QuestionMapper questionMapper;


    @Override
    public List<QuestionType> getQuestionTypes() {
        return questionTypeRepository.findAll();
    }

    @Override
    public QuestionType createQuestionType(CreateQuestionTypeRequest questionType) {
        return questionTypeRepository.save(questionMapper.questionTypeRequestToQuestionType(questionType));
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
