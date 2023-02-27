package com.company.portal.demo.service;

import com.company.portal.demo.entity.Workgroup;
import com.company.portal.demo.payload.request.workgroup.CreateWorkgroupRequest;

import java.util.List;
import java.util.Optional;

public interface WorkgroupService {

    List<Workgroup> getTopLevelWorkgroups();

    List<Workgroup> getSubWorkgroups(Long parentWorkgroupId);

    Workgroup saveWorkgroup(CreateWorkgroupRequest workgroupRequest);

    Optional<Workgroup> getWorkgroupById(Long id);

    void deleteWorkgroup(Workgroup workgroup);

    Workgroup createSubWorkgroup(Long parentId, CreateWorkgroupRequest subWorkgroup);
}
