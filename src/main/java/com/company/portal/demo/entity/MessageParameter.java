package com.company.portal.demo.entity;

import com.company.portal.demo.enums.MessageParameterEnum;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "MESSAGE_PARAMETER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageParameter{

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "MESSAGE_KEY", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private MessageParameterEnum messageKey;

    @Column(name = "MESSAGE_VALUE", nullable = false)
    private String messageValue;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

}