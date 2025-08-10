package com.example.auth_task_api.persistence.model;

import java.io.Serializable;

public record UserHasRolePK(Long user, Long role) implements Serializable {
}
