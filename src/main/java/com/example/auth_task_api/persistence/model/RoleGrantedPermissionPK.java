package com.example.auth_task_api.persistence.model;
import java.io.Serializable;

public record RoleGrantedPermissionPK(Long role, Long permission) implements Serializable {
}

