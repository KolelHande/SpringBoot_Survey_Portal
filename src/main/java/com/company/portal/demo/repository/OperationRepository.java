package com.company.portal.demo.repository;

import com.company.portal.demo.entity.Operation;
import com.company.portal.demo.enums.OperationTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperationRepository extends JpaRepository<Operation,Long> {

    Optional<Operation> findByName(OperationTypeEnum operationTypeEnum);
}
