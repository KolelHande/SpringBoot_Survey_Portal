package com.company.portal.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "USER_SURVEY_RESULT")
public class UserSurveyResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "RESPONSE_DATE")
    private Date responseDate;

    @Column(name = "UPDATED_RESPONSE_COUNT")
    private Integer updatedResponseCount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference
    @JoinColumn(name = "SURVEY_ID")
    private Survey survey;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany( fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_SURVEY_RESULT_ID")
    private Set<Answer> answers;

}
