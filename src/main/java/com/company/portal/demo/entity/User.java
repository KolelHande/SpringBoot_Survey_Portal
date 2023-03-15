package com.company.portal.demo.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
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

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE", nullable = false)
    private Date birthDate;

    @Column(name = "PHONE_NUMBER", nullable = false)
    private String phoneNumber;

    @Email
    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "IMAGE")
    private Byte[] image;

    @Column(name = "RESET_TOKEN")
    private String resetToken;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_WORKGROUP",
            joinColumns = @JoinColumn(name = "USER_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "WORKGROUP_ID", referencedColumnName = "ID"))
    private Set<Workgroup> workgroups;

}
