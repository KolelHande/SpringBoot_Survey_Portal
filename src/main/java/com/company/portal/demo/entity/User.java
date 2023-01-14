package com.company.portal.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //cascade all: user silinirse user details de silinsin, lazy:gerektiği yerde user detailsi kullan, eager is userı çağıran her yerde details de gelir
    @JoinColumn(name = "personal_detail_id", nullable = false)
    private PersonalDetail personalDetail;

}
