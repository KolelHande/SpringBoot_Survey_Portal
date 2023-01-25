package com.company.portal.demo.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "SURVEY")
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "START_DATE")
    private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "MIN_RESPONSE")
    private Integer minResponse;

    @Column(name = "MAX_RESPONSE")
    private Integer maxResponse;

   /* @ManyToMany(mappedBy = "surveys", fetch = FetchType.LAZY)
    private Set<Workgroup> workgroups;*/

    @OneToMany(mappedBy = "survey", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Question> questions;
}
