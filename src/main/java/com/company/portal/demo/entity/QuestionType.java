package com.company.portal.demo.entity;

import com.company.portal.demo.enums.QuestionTypeEnum;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "QUESTION_TYPE")
public class QuestionType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TYPE", unique = true)
    @Enumerated(EnumType.STRING)
    private QuestionTypeEnum type;
}
