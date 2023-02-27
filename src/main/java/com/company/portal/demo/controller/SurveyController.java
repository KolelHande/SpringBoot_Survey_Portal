package com.company.portal.demo.controller;

import com.company.portal.demo.constant.PortalConstant;
import com.company.portal.demo.entity.Survey;
import com.company.portal.demo.entity.UserSurveyResult;
import com.company.portal.demo.payload.base.BaseResponse;
import com.company.portal.demo.payload.dto.*;
import com.company.portal.demo.payload.request.survey.CreateSurveyRequest;
import com.company.portal.demo.payload.request.survey.UpdateSubmittedSurveyRequest;
import com.company.portal.demo.payload.request.survey.UserSurveyResultRequest;
import com.company.portal.demo.service.SurveyService;
import com.company.portal.demo.service.UserSurveyResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
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


    @PostMapping
    public Survey createSurvey(@RequestBody CreateSurveyRequest request) {
        return surveyService.createSurvey(request);
    }

    @GetMapping("/pageable")
    public ResponseEntity<BaseResponse<PageImpl<SurveyDto>>> getAllSurveys(
            @RequestParam(value = "pageNo", defaultValue = PortalConstant.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PortalConstant.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PortalConstant.DEFAULT_SORT_BY_PUBLISH_DATE, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PortalConstant.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        PaginatedSurveyDto paginatedSurveys = surveyService.getPaginatedSurveys(pageNo, pageSize, sortBy, sortDir);
        return ResponseEntity.ok(new BaseResponse<>(new PageImpl<>(paginatedSurveys.getSurveys(),paginatedSurveys.getPageable(), paginatedSurveys.getCount())));
    }

    @GetMapping("results/pageable")
    public ResponseEntity<BaseResponse<PageImpl<UserSurveyResultDto>>> getAllUserSurveyResults(
            @RequestParam(value = "pageNo", defaultValue = PortalConstant.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = PortalConstant.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = PortalConstant.DEFAULT_SORT_BY_RESPONSE_DATE, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PortalConstant.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        PaginatedUserSurveyResultDto paginatedUserSurveyResults = userSurveyResultService.getPaginatedUserSurveyResults(pageNo, pageSize, sortBy, sortDir);
        return ResponseEntity.ok(new BaseResponse<>(new PageImpl<>(paginatedUserSurveyResults.getUserSurveyResults(),paginatedUserSurveyResults.getPageable(), paginatedUserSurveyResults.getCount())));
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
    @PostMapping("/{surveyId}/results")
    public ResponseEntity<UserSurveyResult> createUserSurveyResult(@PathVariable Long surveyId, @RequestBody UserSurveyResultRequest request) {

        UserSurveyResult surveyResponse = userSurveyResultService.createUserSurveyResult(surveyId, request);
        return new ResponseEntity<>(surveyResponse, HttpStatus.CREATED);
    }

    @GetMapping("/results/{surveyId}/user/{userId}")
    public ResponseEntity<BaseResponse<UserSurveyResult>> getSurveyResultByUserIdAndSurveyId(@PathVariable Long surveyId, @PathVariable Long userId) {
        return ResponseEntity.ok(new BaseResponse<>(userSurveyResultService.getSurveyResultByUserIdAndSurveyId(surveyId, userId)));
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

    @GetMapping("/{surveyId}/results/users")
    public ResponseEntity<BaseResponse<List<UserDto>>> getUsersBySurveyId(@PathVariable Long surveyId) {
        return ResponseEntity.ok(new BaseResponse<>(userSurveyResultService.getSurveyUsersBySurveyId(surveyId)));
    }

    @GetMapping("/{surveyId}/results/user/{userId}/answers")
    public ResponseEntity<BaseResponse<UserSurveyResultAnswerDto>> getAnswersByUserIdAndSurveyId(@PathVariable Long surveyId, @PathVariable Long userId) {
        return ResponseEntity.ok(new BaseResponse<>(userSurveyResultService.getUserSurveyResultAnswerByUserIdAndSurveyId(surveyId, userId)));
    }



}
