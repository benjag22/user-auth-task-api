package com.example.auth_task_api.service;

import com.example.auth_task_api.persistence.model.Users;
import com.example.auth_task_api.persistence.repository.UsersRepository;
import org.springframework.stereotype.Service;

@Service
public class UsersService {

    private UsersRepository usersRepository;

    public Users registerUser(Users user) {
        return usersRepository.save(user);
    }
}
