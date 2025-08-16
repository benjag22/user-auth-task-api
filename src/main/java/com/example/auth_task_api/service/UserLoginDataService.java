package com.example.auth_task_api.service;

import com.example.auth_task_api.Security.SecurityUtil;
import com.example.auth_task_api.api.dto.Auth.TokenResponse;
import com.example.auth_task_api.api.dto.Users.UserLoginRequestDto;
import com.example.auth_task_api.api.dto.Users.UsersCreateRequestDto;
import com.example.auth_task_api.api.validate.UserLoginDataValidator;
import com.example.auth_task_api.persistence.model.UserLoginData;
import com.example.auth_task_api.persistence.model.Users;
import com.example.auth_task_api.persistence.repository.UserLoginDataRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginDataService {
    private final UserLoginDataRepository userLoginDataRepository;
    private final UserLoginDataValidator userLoginDataValidator;
    private final JWTService jwtService;

    @Autowired
    public UserLoginDataService(UserLoginDataRepository userLoginDataRepository, JWTService jwtService, UserLoginDataValidator userLoginDataValidator) {
        this.userLoginDataRepository = userLoginDataRepository;
        this.jwtService = jwtService;
        this.userLoginDataValidator = userLoginDataValidator;
    }

    @Transactional
    public UserLoginData createUserLoginData(UsersCreateRequestDto userLoginData, Users user) {

        SecurityUtil securityUtil = new SecurityUtil();
        String password = userLoginData.getPassword();
        String nickname = userLoginData.getNickname();
        String emailAddress = userLoginData.getEmailAddress();
        String hashPassword = securityUtil.hashPassword(password);

        UserLoginData newUserLoginData = UserLoginData
                .builder()
                .user(user)
                .emailAddress(emailAddress)
                .nickname(nickname)
                .passwordHash(hashPassword)
                .build();

        UserLoginData savedUserLoginData = userLoginDataRepository.save(newUserLoginData);
        String token = jwtService.generateToken(savedUserLoginData);
        savedUserLoginData.setSessionToken(token);
        return userLoginDataRepository.save(savedUserLoginData);
    }

    @Transactional
    public TokenResponse attempLogin(UserLoginRequestDto userLoginData) {

        userLoginDataValidator.validateOnLogin(userLoginData);

        String emailAddress = userLoginData.getEmailAddress();
        UserLoginData savedUserLoginData = userLoginDataRepository.findByEmailAddress(emailAddress);
        String token = jwtService.generateToken(savedUserLoginData);
        savedUserLoginData.setSessionToken(token);
        userLoginDataRepository.save(savedUserLoginData);

        return new TokenResponse(token);
    }

    @Transactional
    public String attempLogout(String token) {
        UserLoginData userLoginData = userLoginDataRepository.findBySessionToken(token);
        userLoginData.setSessionToken(null);
        userLoginDataRepository.save(userLoginData);
        return "log out successfully";
    }
}
