package com.company.portal.demo.repository;

import com.company.portal.demo.entity.UserSurveyResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSurveyResponseRepository extends JpaRepository<UserSurveyResponse, Long> {

   Optional<UserSurveyResponse> findBySurveyIdAndUserId(Long surveyId, Long userId);
}
