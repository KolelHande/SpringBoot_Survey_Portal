package com.company.portal.demo.service.impl;

import com.company.portal.demo.entity.Answer;
import com.company.portal.demo.repository.AnswerRepository;
import com.company.portal.demo.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    @Override
    public Answer getAnswerById(Long id) {
        return answerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Answer with id " + id + " not found."));
    }

    @Override
    public Set<Answer> getAnswerByIds(Set<Long> answerIds) {
        return answerIds.stream()
                .map(answerRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

}
