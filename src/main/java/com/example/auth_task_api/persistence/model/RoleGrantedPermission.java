package com.example.auth_task_api.persistence.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "role_granted_permission")
@Getter
@Setter
@Builder
@EqualsAndHashCode(exclude = {"role", "permission"})
@AllArgsConstructor
@NoArgsConstructor
@IdClass(RoleGrantedPermissionPK.class)

public class RoleGrantedPermission {
    @Id
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @Id
    @ManyToOne
    @JoinColumn(name = "permission_id")
    private Permission permission;


}
