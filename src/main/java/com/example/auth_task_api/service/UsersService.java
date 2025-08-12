package com.example.auth_task_api.service;

import com.example.auth_task_api.api.dto.Auth.TokenResponse;
import com.example.auth_task_api.api.dto.Users.UsersCreateRequestDto;
import com.example.auth_task_api.api.validate.UserLoginDataValidator;
import com.example.auth_task_api.persistence.model.UserLoginData;
import com.example.auth_task_api.persistence.model.Users;
import com.example.auth_task_api.persistence.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final UserLoginDataService userLoginDataService;
    private final UserLoginDataValidator userLoginDataValidator;

    @Autowired
    public UsersService(UsersRepository usersRepository, UserLoginDataService userLoginDataService, UserLoginDataValidator userLoginDataValidator) {
        this.usersRepository = usersRepository;
        this.userLoginDataService = userLoginDataService;
        this.userLoginDataValidator = userLoginDataValidator;
    }

    @Transactional
    public TokenResponse registerUser(UsersCreateRequestDto userDto) {
        userLoginDataValidator.validateOnCreate(userDto);
        String firstName = userDto.getFirstName();
        String lastName = userDto.getLastName();
        String middleName = null;
        if (userDto.getMiddleName() != null && !userDto.getMiddleName().trim().isEmpty()) {
            middleName = userDto.getMiddleName().trim();
        }

        Users newUser = Users.builder()
                .firstName(firstName)
                .lastName(lastName)
                .middleName(middleName)
                .creationDate(LocalDateTime.now())
                .build();

        System.out.println("Creation date: "+newUser.getCreationDate());
        Users savedUser = usersRepository.save(newUser);

        UserLoginData savedUserLoginData = userLoginDataService.createUserLoginData(userDto, savedUser);
        return new TokenResponse(savedUserLoginData.getSessionToken());
    }
}
