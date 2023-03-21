package com.company.portal.demo.repository;

import com.company.portal.demo.entity.Question;
import com.company.portal.demo.entity.Survey;
import com.company.portal.demo.entity.Workgroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface SurveyRepository extends JpaRepository <Survey, Long> {

    @Query(value = "SELECT s.questions FROM Survey s WHERE s.id = :surveyId")
    List<Question> findQuestionsBySurveyId(Long surveyId);

    Page<Survey> findByWorkgroupsIn(Set<Workgroup> workgroups, Pageable pageable);
}
