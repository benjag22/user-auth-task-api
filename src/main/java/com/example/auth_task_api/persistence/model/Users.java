package com.example.auth_task_api.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    private Long id;

    @Column(name = "creation_date")
    @Getter
    @Setter
    private LocalDateTime creationDate;

    @Column(name = "first_name", length = 100, nullable = false)
    @Getter
    @Setter
    private String firstName;

    @Column(name = "middle_name", length = 100)
    @Getter
    @Setter
    private String middleName;

    @Column(name = "last_name", length = 100, nullable = false)
    @Getter
    @Setter
    private String lastName;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private UserLoginData userLoginData;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserHasRole> roles = new LinkedHashSet<>();

}
