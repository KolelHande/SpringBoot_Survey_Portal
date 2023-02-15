package com.company.portal.demo.controller;

import com.company.portal.demo.entity.Survey;
import com.company.portal.demo.entity.UserSurveyResult;
import com.company.portal.demo.payload.base.BaseResponse;
import com.company.portal.demo.payload.request.survey.UserSurveyResultRequest;
import com.company.portal.demo.payload.request.survey.CreateSurveyRequest;
import com.company.portal.demo.payload.request.survey.UpdateSubmittedSurveyRequest;
import com.company.portal.demo.service.SurveyService;
import com.company.portal.demo.service.UserSurveyResultService;
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
    private final UserSurveyResultService userSurveyResultService;

    @GetMapping("/getAllSurveys")
    public ResponseEntity<BaseResponse<List<Survey>>> getAllSurveys(){

        return ResponseEntity.ok(new BaseResponse<>(surveyService.getAllSurveys()));
        //return new ResponseEntity<>(surveyService.getAllSurveys(),HttpStatus.OK);
    }

    @PostMapping
    public Survey createSurvey(@RequestBody CreateSurveyRequest request) {
        return surveyService.createSurvey(request);
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
    @PostMapping("/results/{surveyId}")
    public ResponseEntity<UserSurveyResult> createUserSurveyResult(@PathVariable Long surveyId, @RequestBody UserSurveyResultRequest request) {

        UserSurveyResult surveyResponse = userSurveyResultService.createUserSurveyResult(surveyId, request);
        return new ResponseEntity<>(surveyResponse, HttpStatus.CREATED);
    }

    @GetMapping("/results/{surveyId}/user/{userId}")
    public ResponseEntity<UserSurveyResult> getSurveyResultByUserIdAndSurveyId(@PathVariable Long surveyId, @PathVariable Long userId) {
        return new ResponseEntity<>(userSurveyResultService.getSurveyResultByUserIdAndSurveyId(surveyId, userId), HttpStatus.OK);
    }

    @GetMapping("/results")
    public ResponseEntity<List<UserSurveyResult>> getAllSurveyResults() {
        List<UserSurveyResult> surveyResponses = userSurveyResultService.getAllSurveyResult();

        return new ResponseEntity<>(surveyResponses, HttpStatus.OK);
    }

    @PutMapping("/results/{surveyId}}")
    public ResponseEntity<UserSurveyResult> updateUserSurveyResult(@PathVariable Long surveyId, @RequestBody UserSurveyResultRequest request) {
        return new ResponseEntity<>(userSurveyResultService.updateUserSurveyResult(surveyId, request), HttpStatus.OK);
    }

}
