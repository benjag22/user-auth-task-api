package com.example.auth_task_api.api.dto.Users;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsersCreateRequestDto {

    @NotBlank(message = "The first name cannot be empty")
    private String firstName;

    private String middleName;

    @NotBlank(message = "The last name cannot by empty")
    private String lastName;

    @Email(message = "Email should be valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email_adress;

    private String nickname;

    @NotBlank(message = "cannot be empty")
    @Size(min = 7, max = 15)
    private String password;

}
