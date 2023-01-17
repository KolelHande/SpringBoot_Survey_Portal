package com.company.portal.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "DEPARTMENT",uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME"})})
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIRECTOR_ID", nullable = false)
    private Director director;

    @OneToMany(mappedBy = "department",fetch = FetchType.LAZY)
    private Set<Workgroup> workgroups;

}
