package com.example.auth_task_api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Ping {

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

}
