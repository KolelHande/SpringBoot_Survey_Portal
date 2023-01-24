package com.company.portal.demo.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "QUESTION_OPTION")
public class QuestionOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ORDER_NUMBER")
    private Integer orderNumber;

    @Column(name = "VALUE")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "QUESTION_ID", nullable = false)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "ANSWER_OPTION",
            joinColumns = @JoinColumn(name = "QUESTION_OPTION_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ANSWER_ID", referencedColumnName = "ID"))
    private Answer answer;
}
