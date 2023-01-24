package com.company.portal.demo.entity;

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
@Table(name = "USER", uniqueConstraints = {@UniqueConstraint(columnNames = {"USERNAME"}), @UniqueConstraint(columnNames = {"EMAIL"})})
// burda username unique + email unique ayrı ayrı
//@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "personNumber", "isActive" }) }) bu kullanım şeklinde ikinin kombinasyonu unique
public class User {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SURNAME", nullable = false)
    private String surname;

    @Column(name = "USERNAME", nullable = false)
    private String userName;

    @Column(name = "ENCRYPTED_PASSWORD", nullable = false)
    private String encryptedPassword;

    @Column(name = "BIRTH_DATE", nullable = false)
    private Date birthDate;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "IMAGE")
    private Byte[] image;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_WORKGROUP",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "WORKGROUP_ID", referencedColumnName = "ID"))
    private Set<Workgroup> workgroups;

}
