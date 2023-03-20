package com.company.portal.demo.repository;

import com.company.portal.demo.entity.MessageParameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageParameterRepository extends JpaRepository<MessageParameter, Long> {
}