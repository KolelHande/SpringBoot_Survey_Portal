package com.company.portal.demo.repository;

import com.company.portal.demo.entity.UserSurveyResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserSurveyResultRepository extends JpaRepository<UserSurveyResult, Long> {

   Optional<UserSurveyResult> findBySurveyIdAndUserId(Long surveyId, Long userId);
}
