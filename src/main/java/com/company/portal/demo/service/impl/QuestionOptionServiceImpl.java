package com.company.portal.demo.service.impl;

import com.company.portal.demo.entity.QuestionOption;
import com.company.portal.demo.repository.QuestionOptionRepository;
import com.company.portal.demo.service.QuestionOptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionOptionServiceImpl implements QuestionOptionService {

    private final QuestionOptionRepository questionOptionRepository;


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

    @Override
    public Set<QuestionOption> getAnswerOptionsByIds(Set<Long> answerOptionIds) {
        return answerOptionIds.stream()
                .map(questionOptionRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }
}