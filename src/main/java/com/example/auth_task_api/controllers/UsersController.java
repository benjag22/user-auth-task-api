package com.example.auth_task_api.controllers;

import com.example.auth_task_api.api.dto.Auth.TokenResponse;
import com.example.auth_task_api.api.dto.Users.UsersCreateRequestDto;
import com.example.auth_task_api.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final UsersService usersService;

    public UsersController(final UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/create")
    public ResponseEntity<TokenResponse> create(@Valid @RequestBody UsersCreateRequestDto user) {
        final TokenResponse token = usersService.registerUser(user);
        return ResponseEntity.ok(token);
    }
}
