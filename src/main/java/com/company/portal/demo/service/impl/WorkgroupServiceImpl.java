package com.company.portal.demo.service.impl;

import com.company.portal.demo.entity.Workgroup;
import com.company.portal.demo.exception.ResourceNotFoundException;
import com.company.portal.demo.mapper.workgroup.WorkgroupMapper;
import com.company.portal.demo.payload.request.workgroup.CreateWorkgroupRequest;
import com.company.portal.demo.repository.WorkgroupRepository;
import com.company.portal.demo.service.WorkgroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkgroupServiceImpl implements WorkgroupService {

    private final WorkgroupRepository workgroupRepository;
    private final WorkgroupMapper workgroupMapper;

    @Override
    public List<Workgroup> getTopLevelWorkgroups() {
        return workgroupRepository.findByParentWorkgroupIsNull();
    }

    @Override
    public List<Workgroup> getSubWorkgroups(Long parentWorkgroupId) {
        return workgroupRepository.findByParentWorkgroupId(parentWorkgroupId);
    }

    @Override
    public Workgroup saveWorkgroup(CreateWorkgroupRequest workgroupRequest) {
        return workgroupRepository.save(workgroupMapper.workgroupRequestToWorkgroup(workgroupRequest));
    }

    @Override
    public Optional<Workgroup> getWorkgroupById(Long id) {
        return workgroupRepository.findById(id);
    }

    @Override
    public void deleteWorkgroup(Workgroup workgroup) {
        workgroupRepository.delete(workgroup);
    }

    @Override
    public Workgroup createSubWorkgroup(Long parentId, CreateWorkgroupRequest subWorkgroup) {
        Workgroup parentWorkgroup = workgroupRepository.findById(parentId)
                .orElseThrow(() -> new ResourceNotFoundException("Workgroup", "parent workgroup" , parentId));

        Workgroup newSubWorkgroup = workgroupMapper.workgroupRequestToWorkgroup(subWorkgroup);
        newSubWorkgroup.setParentWorkgroup(parentWorkgroup);
        workgroupRepository.save(newSubWorkgroup);

        return newSubWorkgroup;
    }
}