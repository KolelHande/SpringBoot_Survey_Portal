package com.company.portal.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "QUESTION")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TEXT")
    private String text;

    @Column(name = "ORDER_NUMBER")
    private Integer orderNumber;

    @Column(name = "IS_OPTIONAL")
    private Boolean optional;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "SURVEY_ID", nullable = false)
    private Survey survey;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "QUESTION_TYPE_ID", nullable = false)
    private QuestionType questionType;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<QuestionOption> questionOptions;
}
