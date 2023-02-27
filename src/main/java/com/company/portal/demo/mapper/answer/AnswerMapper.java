package com.company.portal.demo.mapper.answer;

import com.company.portal.demo.entity.Answer;
import com.company.portal.demo.entity.QuestionOption;
import com.company.portal.demo.payload.dto.AnswerDto;
import com.company.portal.demo.payload.request.answer.AnswerRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface AnswerMapper {
    AnswerRequest answerToAnswerDto(Answer answer);

    default List<AnswerDto> answerListToAnswerDtoList(List<Answer> answers){
        if ( answers == null ) {
            return null;
        }

        List<AnswerDto> list = new ArrayList<AnswerDto>( answers.size() );
        for ( Answer answer : answers ) {

            Set<Long> answerOptionIds = null;
            if(!CollectionUtils.isEmpty(answer.getAnswerOptions())){
                answerOptionIds = answer.getAnswerOptions().stream()
                        .map(QuestionOption::getId)
                        .collect(Collectors.toSet());
            }

            AnswerDto answerDto = AnswerDto.builder()
                    .answerText(answer.getAnswerText())
                    .answerOptionIds(answerOptionIds)
                    .questionId(answer.getQuestion().getId())
                    .build();
            list.add(answerDto);
        }
        return list;
    }
}
