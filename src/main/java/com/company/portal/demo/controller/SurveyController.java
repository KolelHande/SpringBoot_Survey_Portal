package com.company.portal.demo.controller;

import com.company.portal.demo.entity.Survey;
import com.company.portal.demo.payload.request.survey.UpdateSubmittedSurveyRequest;
import com.company.portal.demo.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/surveys")
public class SurveyController {

    private final SurveyService surveyService;

    @GetMapping("/getAllSurveys")
    public List<Survey> getAllSurveys(){return surveyService.getAllSurveys();}

    @PostMapping
    public Survey createSurvey(@RequestBody Survey survey) {
        return surveyService.createSurvey(survey);
    }

    @GetMapping("/{id}")
    public Survey getSurveyById(@PathVariable Long id) {
        return surveyService.getSurveyById(id);
    }

    @PutMapping("/updateSurvey/{surveyId}")
    public ResponseEntity<Survey> updateDateOrWorkgroup(@PathVariable Long surveyId, @RequestBody UpdateSubmittedSurveyRequest request){

        Survey updatedSurvey= surveyService.updateSubmittedSurvey(surveyId,request);
        return new ResponseEntity<>(updatedSurvey, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public void deleteSurvey(@PathVariable Long id) {
        surveyService.deleteSurvey(id);
    }


}
