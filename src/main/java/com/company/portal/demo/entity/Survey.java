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

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_DATE", nullable = false)
    private Date createDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "PUBLISH_DATE", nullable = false)
    private Date publishDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "END_DATE", nullable = false)
    private Date endDate;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "SURVEY_WORKGROUP",
            joinColumns = @JoinColumn(name = "SURVEY_ID"),
            inverseJoinColumns = @JoinColumn(name = "WORKGROUP_ID"))
    private Set<Workgroup> workgroups;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "SURVEY_ID", nullable = false)
    private Set<Question> questions;

    @OneToMany(mappedBy = "survey", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<UserSurveyResult> surveyResults;
}

