package com.example.auth_task_api.persistence.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_has_role")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"role", "user"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(UserHasRolePK.class)
public class UserHasRole {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    @Id
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
}
