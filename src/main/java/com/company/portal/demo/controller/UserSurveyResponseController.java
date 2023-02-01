package com.company.portal.demo.controller;

import com.company.portal.demo.entity.UserSurveyResponse;
import com.company.portal.demo.payload.dto.UserSurveyResponseDto;
import com.company.portal.demo.service.UserSurveyResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserSurveyResponseController {
    private final UserSurveyResponseService surveyResponseService;

    @PostMapping
    public ResponseEntity<UserSurveyResponse> createSurveyResponse(@RequestBody UserSurveyResponseDto surveyResponseDto) {

        UserSurveyResponse surveyResponse= surveyResponseService.createSurveyResponse(surveyResponseDto);
        return new ResponseEntity<>(surveyResponse, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserSurveyResponse> getSurveyResponseById(@PathVariable Long id) {
        UserSurveyResponse surveyResponse = surveyResponseService.getSurveyResponseById(id);

        if (surveyResponse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(surveyResponse, HttpStatus.OK);
    }

    @GetMapping("/survey/{surveyId}/user/{userId}")
    public ResponseEntity<UserSurveyResponse> getSurveyResponseByUserIdAndSurveyId(@PathVariable Long surveyId, @PathVariable Long userId){
        return new ResponseEntity<>(surveyResponseService.getSurveyResponseByUserIdAndSurveyId(surveyId, userId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserSurveyResponse>> getAllSurveyResponses() {
        List<UserSurveyResponse> surveyResponses = surveyResponseService.getAllSurveyResponses();

        return new ResponseEntity<>(surveyResponses, HttpStatus.OK);
    }
}
