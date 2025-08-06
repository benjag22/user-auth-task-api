package com.example.auth_task_api.persistence.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "role")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "grantedPermissions")
@ToString(exclude = "grantedPermissions")

public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description", length = 128)
    private String description;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<RoleGrantedPermission> grantedPermissions  = new LinkedHashSet<>();

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private Set<UserHasRole> users  = new LinkedHashSet<>();
}
