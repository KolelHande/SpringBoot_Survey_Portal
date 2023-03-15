package com.company.portal.demo.entity;

import lombok.*;
import javax.persistence.*;
@Entity
@Table(name = "MESSAGE_TEMPLATE")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageTemplate {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OPERATION_ID", nullable = false)
    private Operation operation;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "RECIPIENT")
    private String recipient;

    @Column(name = "RECIPIENT_CC")
    private String recipientCC;

}