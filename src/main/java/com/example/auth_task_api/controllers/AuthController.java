package com.example.auth_task_api.controllers;

import com.example.auth_task_api.api.dto.Auth.TokenResponse;
import com.example.auth_task_api.api.dto.Users.UserLoginRequestDto;
import com.example.auth_task_api.service.UserLoginDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    UserLoginDataService userLoginDataService;

    AuthController(UserLoginDataService userLoginDataService) {
        this.userLoginDataService = userLoginDataService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody UserLoginRequestDto userLoginRequestDto) {
        final TokenResponse token = userLoginDataService.attempLogin(userLoginRequestDto);
        return ResponseEntity.ok(token);
    }

}
