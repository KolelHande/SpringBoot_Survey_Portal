package com.company.portal.demo.entity;

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

    @Column(name = "TEXT", nullable = false)
    private String text;

    @Column(name = "ORDER_NUMBER", nullable = false)
    private Integer orderNumber;

    @Column(name = "IS_OPTIONAL", nullable = false)
    private Boolean optional;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "QUESTION_TYPE_ID", nullable = false)
    private QuestionType questionType;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "QUESTION_ID", nullable = false)
    private List<QuestionOption> questionOptions;
}
