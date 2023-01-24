package com.company.portal.demo.controller;

import com.company.portal.demo.entity.Survey;
import com.company.portal.demo.service.SurveyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/surveys")
public class SurveyController {
    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }
    @GetMapping
    public List<Survey> getAllSurveys() {
        return surveyService.getAllSurveys();
    }

   /* @PostMapping
    public Survey createSurvey(@RequestBody SurveyDto surveyDto) {
        return surveyService.createSurvey(surveyDto);
    }
    */

    @DeleteMapping("/{id}")
    public void deleteSurvey(@PathVariable Long id) {
        surveyService.deleteSurvey(id);
    }
}
