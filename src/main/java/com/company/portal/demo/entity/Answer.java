package com.company.portal.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "ANSWER")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ANSWER_TEXT")
    private String answerText;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID", nullable = false)
    private Question question;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ANSWER_OPTION",
            joinColumns = @JoinColumn(name = "ANSWER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "QUESTION_OPTION_ID", referencedColumnName = "ID"))
    private Set<QuestionOption> answerOptions;


}
