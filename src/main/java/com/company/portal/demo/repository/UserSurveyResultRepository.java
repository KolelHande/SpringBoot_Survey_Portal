package com.company.portal.demo.repository;

import com.company.portal.demo.entity.Answer;
import com.company.portal.demo.entity.User;
import com.company.portal.demo.entity.UserSurveyResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserSurveyResultRepository extends JpaRepository<UserSurveyResult, Long> {

   Optional<UserSurveyResult> findBySurveyIdAndUserId(Long surveyId, Long userId);

   // nativeQuery=true ise jpql değil de, sorgu doğrudan SQL olarak çalıştırılır - sql de yazdığım sorguyunun aynısını çalıştırmak istiyorsam.
   @Query(value = "SELECT ur.user FROM UserSurveyResult ur WHERE ur.survey.id = :surveyId")
   List<User> findUserBySurveyId(Long surveyId);

   @Query(value = "SELECT ur.answers FROM UserSurveyResult ur WHERE ur.survey.id = :surveyId and  ur.user.id = :userId ")
   List<Answer> findAnswerBySurveyIdAndUserId(Long surveyId, Long userId);

   Optional<UserSurveyResult> findUserSurveyResultAnswerBySurveyIdAndUserId(Long surveyId, Long userId);
}
