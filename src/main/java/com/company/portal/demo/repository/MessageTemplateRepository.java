package com.company.portal.demo.repository;

import com.company.portal.demo.entity.MessageTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MessageTemplateRepository extends JpaRepository<MessageTemplate,Long> {

    Optional<MessageTemplate> findByOperationId(Long operationId);
}
