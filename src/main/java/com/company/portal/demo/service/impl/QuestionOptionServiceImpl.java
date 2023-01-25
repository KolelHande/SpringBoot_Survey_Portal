package com.company.portal.demo.service.impl;

import com.company.portal.demo.entity.QuestionOption;
import com.company.portal.demo.repository.QuestionOptionRepository;
import com.company.portal.demo.service.QuestionOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionOptionServiceImpl implements QuestionOptionService {
    @Autowired
    private QuestionOptionRepository questionOptionRepository;

    @Override
    public QuestionOption getOptionById(Long id) {
        return questionOptionRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteOption(Long id) {
        questionOptionRepository.deleteById(id);
    }

    @Override
    public QuestionOption createOption(QuestionOption option) {
        return questionOptionRepository.save(option);
    }

}