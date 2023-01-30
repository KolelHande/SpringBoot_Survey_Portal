package com.company.portal.demo.controller;

import com.company.portal.demo.entity.Answer;
import com.company.portal.demo.payload.dto.AnswerDto;
import com.company.portal.demo.service.AnswerService;
import com.company.portal.demo.service.QuestionOptionService;
import com.company.portal.demo.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/answer")
public class AnswerController {
private final AnswerService answerService;
private final QuestionService questionService;
private final QuestionOptionService answerOptionService;

    @PostMapping("/createAnswer")
    public ResponseEntity<Answer> createAnswer( @RequestBody AnswerDto answerDto) {
       // Question question = questionService.getQuestionById(answerDto.getQuestionId()); Set<QuestionOption> answerOptions = answerOptionService.getAnswerOptionsByIds(answerDto.getAnswerOptionIds());
        Answer answer = answerService.createAnswer(answerDto);
        return new ResponseEntity<>(answer, HttpStatus.CREATED);
    }

    @GetMapping("/getAnswerById/{id}")
    public  Answer getAnswerById(@PathVariable Long id){
        return answerService.getAnswerById(id);
    }
}
