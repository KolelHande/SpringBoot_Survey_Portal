package com.company.portal.demo.controller;

import com.company.portal.demo.entity.QuestionType;
import com.company.portal.demo.payload.request.question.CreateQuestionTypeRequest;
import com.company.portal.demo.service.QuestionTypeService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/question-types")
public class QuestionTypeController {
    private final QuestionTypeService questionTypeService;

    public QuestionTypeController(QuestionTypeService questionTypeService) {
        this.questionTypeService = questionTypeService;
    }

    @GetMapping
    public List<QuestionType> getQuestionTypes() {
        return questionTypeService.getQuestionTypes();
    }

    @PostMapping
    public QuestionType createQuestionType(@Valid @RequestBody CreateQuestionTypeRequest request) {
        return questionTypeService.createQuestionType(request);
    }

    @DeleteMapping("/{id}")
    public void deleteQuestionType(@PathVariable Long id) {
        questionTypeService.deleteQuestionType(id);
    }
}
