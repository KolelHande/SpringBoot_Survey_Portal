package com.company.portal.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "WORKGROUP")
public class Workgroup {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties("subWorkgroup")
    private Workgroup parentWorkgroup;

    @OneToMany(mappedBy = "parentWorkgroup")
    private Set<Workgroup> subWorkgroup;


   /* @ManyToMany(mappedBy = "workgroups", fetch = FetchType.LAZY)
    private Set<User> users;

  @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "SURVEY_WORKGROUP",
            joinColumns = @JoinColumn(name = "SURVEY_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "WORKGROUP_ID", referencedColumnName = "ID"))
    private Set<Survey> surveys;*/

}