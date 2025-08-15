package com.example.auth_task_api.api.dto.Users;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginRequestDto {

    @NotBlank(message = "Email is required")
    private String emailAddress;

    @NotBlank(message = "Password is required")
    private String password;
}
