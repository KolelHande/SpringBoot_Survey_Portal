package com.company.portal.demo.service.impl;

import com.company.portal.demo.entity.Question;
import com.company.portal.demo.entity.Survey;
import com.company.portal.demo.mapper.survey.SurveyMapper;
import com.company.portal.demo.payload.dto.PaginatedSurveyDto;
import com.company.portal.demo.payload.dto.SurveyDto;
import com.company.portal.demo.payload.request.survey.CreateSurveyRequest;
import com.company.portal.demo.payload.request.survey.UpdateSubmittedSurveyRequest;
import com.company.portal.demo.repository.SurveyRepository;
import com.company.portal.demo.repository.UserRepository;
import com.company.portal.demo.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class SurveyServiceImpl implements SurveyService {

    private final SurveyRepository surveyRepository;
    private final UserRepository userRepository;
    private final SurveyMapper surveyMapper;

    @Override
    @Transactional
    public Survey createSurvey(CreateSurveyRequest surveyRequest) {

        Survey survey = surveyMapper.createSurveyRequestToSurvey(surveyRequest);
        survey.setCreateDate(new Date());

        return surveyRepository.save(survey);
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

    @Override
    public List<Question> getQuestionsBySurveyId(Long surveyId) {
        return surveyRepository.findQuestionsBySurveyId(surveyId);
    }

    @Override
    public PaginatedSurveyDto getPaginatedSurveys(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Survey> surveys = surveyRepository.findAll(pageable);

        List<SurveyDto> content = surveyMapper.surveyListToSurveyDtoList(surveys.getContent());

        return PaginatedSurveyDto.builder()
                .pageable(pageable)
                .surveys(content)
                .count(surveys.getTotalElements())
                .page(surveys.getPageable().getPageNumber())
                .size(surveys.getPageable().getPageSize())
                .totalPageNumber(surveys.getTotalPages())
                .build();
    }

}