package com.company.portal.demo.repository;

import com.company.portal.demo.entity.Question;
import com.company.portal.demo.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyRepository extends JpaRepository <Survey, Long> {

    @Query(value = "SELECT s.questions FROM Survey s WHERE s.id = :surveyId")
    List<Question> findQuestionsBySurveyId(Long surveyId);
}
