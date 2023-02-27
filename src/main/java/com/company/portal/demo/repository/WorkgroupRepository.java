package com.company.portal.demo.repository;

import com.company.portal.demo.entity.Workgroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkgroupRepository extends JpaRepository<Workgroup, Long> {

    List<Workgroup> findByParentWorkgroupIsNull();

    List<Workgroup> findByParentWorkgroupId(Long parentId);

    Workgroup findByName(String name);
}