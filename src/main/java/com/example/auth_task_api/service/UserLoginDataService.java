package com.example.auth_task_api.service;

import com.example.auth_task_api.Security.SecurityUtil;
import com.example.auth_task_api.api.dto.Users.UsersCreateRequestDto;
import com.example.auth_task_api.persistence.model.UserLoginData;
import com.example.auth_task_api.persistence.model.Users;
import com.example.auth_task_api.persistence.repository.UserLoginDataRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginDataService {
    private final UserLoginDataRepository userLoginDataRepository;
    private final JWTService jwtService;

    @Autowired
    public UserLoginDataService(UserLoginDataRepository userLoginDataRepository, JWTService jwtService) {
        this.userLoginDataRepository = userLoginDataRepository;
        this.jwtService = jwtService;
    }

    @Transactional
    public UserLoginData createUserLoginData(UsersCreateRequestDto userLoginData, Users user) {
        SecurityUtil securityUtil = new SecurityUtil();
        String salt = securityUtil.generateSalt();
        String password = userLoginData.getPassword();
        String nickname = userLoginData.getNickname();
        String emailAdress = userLoginData.getEmail_adress();
        String hashPassword = securityUtil.hashPassword(password, salt);
        UserLoginData newUserLoginData = UserLoginData
                .builder()
                .user(user)
                .emailAdress(emailAdress)
                .nickname(nickname)
                .passwordHash(hashPassword)
                .passwordSalt(salt)
                .build();
        UserLoginData savedUserLoginData = userLoginDataRepository.save(newUserLoginData);
        savedUserLoginData.setSessionToken(jwtService.generateToken(savedUserLoginData));
        return userLoginDataRepository.save(savedUserLoginData);
    }
}
