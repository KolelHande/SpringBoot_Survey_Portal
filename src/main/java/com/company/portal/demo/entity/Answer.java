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

    @Column(name = "RESPONSE_ID")
    private Long responseId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID", nullable = false)
    private Question question;

    @OneToMany(mappedBy = "answer",fetch = FetchType.EAGER)
    private Set<QuestionOption> answerOptions;
    

}
