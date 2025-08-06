package com.example.auth_task_api.controllers;

import com.example.auth_task_api.persistence.model.Users;
import com.example.auth_task_api.service.UsersService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UsersController {

    private final UsersService usersService;

    public UsersController(final UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping("/create")
    public String create() {
        usersService.registerUser(new Users());
    }
}
