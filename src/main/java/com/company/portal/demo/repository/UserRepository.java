package com.company.portal.demo.repository;

import com.company.portal.demo.entity.Survey;
import com.company.portal.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailOrUserName(String email, String userName);

    Optional<User> findByUserName(String userName);

    Boolean existsByUserName(String userName);

    Boolean existsByEmailOrUserName(String email, String userName);

    User findByResetToken(String resetToken);

    @Query("SELECT s FROM Survey s JOIN s.workgroups wg WHERE wg.id = :workgroupId")
    Page<Survey> findByWorkgroupId(@Param("workgroupId") Long workgroupId, Pageable pageable);

}
