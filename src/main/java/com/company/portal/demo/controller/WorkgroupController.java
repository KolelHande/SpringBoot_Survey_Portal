package com.company.portal.demo.controller;

import com.company.portal.demo.entity.Workgroup;
import com.company.portal.demo.payload.request.workgroup.CreateWorkgroupRequest;
import com.company.portal.demo.service.WorkgroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/workgroups")
public class WorkgroupController {

    private final WorkgroupService workgroupService;

    @PostMapping
    public ResponseEntity<Workgroup> createWorkgroup(@RequestBody CreateWorkgroupRequest workgroupRequest) {
        Workgroup savedWorkgroup = workgroupService.saveWorkgroup(workgroupRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWorkgroup);
    }

    @PostMapping("/{parentId}/subworkgroup")
    public ResponseEntity<Workgroup> createSubWorkgroup( @PathVariable("parentId") Long parentId,  @RequestBody CreateWorkgroupRequest subWorkgroup) {
        Workgroup createdSubWorkgroup = workgroupService.createSubWorkgroup(parentId, subWorkgroup);
        return ResponseEntity.created(URI.create("/workgroups/" + createdSubWorkgroup.getId()))
                .body(createdSubWorkgroup);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Workgroup> updateWorkgroup(@PathVariable Long id, @RequestBody CreateWorkgroupRequest workgroupRequest) {
        Optional<Workgroup> existingWorkgroup = workgroupService.getWorkgroupById(id);
        if (existingWorkgroup.isPresent()) {
            Workgroup updatedWorkgroup = workgroupService.saveWorkgroup(workgroupRequest);
            return ResponseEntity.ok(updatedWorkgroup);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/directory")
    public ResponseEntity<List<Workgroup>> getDirectoryWorkgroups() {
        List<Workgroup> directoryWorkgroups = workgroupService.getTopLevelWorkgroups();
        return ResponseEntity.ok(directoryWorkgroups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workgroup> getWorkgroupById(@PathVariable Long id) {
        Optional<Workgroup> workgroup = workgroupService.getWorkgroupById(id);
        return workgroup.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/subWorkgroups")
    public ResponseEntity<List<Workgroup>> getSubWorkgroups(@PathVariable Long id) {
        List<Workgroup> subWorkgroups = workgroupService.getSubWorkgroups(id);
        return ResponseEntity.ok(subWorkgroups);
    }

    @GetMapping("/groups")
    public ResponseEntity<List<Workgroup>> getGroupWorkgroups() {
        List<Workgroup> groupWorkgroups = workgroupService.getSubWorkgroups(null);
        return ResponseEntity.ok(groupWorkgroups);
    }

    @GetMapping("/directory/{id}/groups")
    public ResponseEntity<List<Workgroup>> getDirectorySubWorkgroups(@PathVariable Long id) {
        List<Workgroup> directorySubWorkgroups = workgroupService.getSubWorkgroups(id);
        return ResponseEntity.ok(directorySubWorkgroups);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkgroup(@PathVariable Long id) {
        Optional<Workgroup> workgroup = workgroupService.getWorkgroupById(id);
        if (workgroup.isPresent()) {
            workgroupService.deleteWorkgroup(workgroup.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
