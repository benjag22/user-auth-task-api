package com.example.auth_task_api.controllers;

import com.example.auth_task_api.api.dto.Users.UsersCreateRequestDto;
import com.example.auth_task_api.persistence.model.Users;
import com.example.auth_task_api.service.UsersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UsersController {

    private final UsersService usersService;

    public UsersController(final UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/users")
    public String create(UsersCreateRequestDto user) {
        usersService.registerUser(user);
        return "ok";
    }
}
