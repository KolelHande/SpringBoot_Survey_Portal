package com.company.portal.demo.controller;

import com.company.portal.demo.entity.Survey;
import com.company.portal.demo.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/surveys")
public class SurveyController {
    @Autowired
    private SurveyService surveyService;

    @PostMapping
    public Survey createSurvey(@RequestBody Survey survey) {
        return surveyService.createSurvey(survey);
    }

    @GetMapping("/{id}")
    public Survey getSurveyById(@PathVariable Long id) {
        return surveyService.getSurveyById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteSurvey(@PathVariable Long id) {
        surveyService.deleteSurvey(id);
    }


}
