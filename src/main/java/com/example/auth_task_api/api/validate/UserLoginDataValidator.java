package com.example.auth_task_api.api.validate;

import com.example.auth_task_api.Security.SecurityUtil;
import com.example.auth_task_api.api.dto.Users.UserLoginRequestDto;
import com.example.auth_task_api.api.dto.Users.UsersCreateRequestDto;
import com.example.auth_task_api.api.exceptions.BusinessValidationException;
import com.example.auth_task_api.api.dto.FieldErrorItem;
import com.example.auth_task_api.persistence.model.UserLoginData;
import com.example.auth_task_api.persistence.repository.UserLoginDataRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UserLoginDataValidator {

    private final UserLoginDataRepository userLoginDataRepository;
    private final SecurityUtil securityUtil;

    public UserLoginDataValidator(UserLoginDataRepository userLoginDataRepository) {
        this.userLoginDataRepository = userLoginDataRepository;
        this.securityUtil = new SecurityUtil();
    }

    public void validateOnCreate(UsersCreateRequestDto userRequest) {
        List<FieldErrorItem> errors = new ArrayList<>();

        final String emailAddress = userRequest.getEmailAddress();
        final String nickname = userRequest.getNickname();
        final String password = userRequest.getPassword();

        if (nickname == null || nickname.isBlank()) {
            errors.add(new FieldErrorItem("nickname", "Nickname cannot be blank"));
        } else if (userLoginDataRepository.existsByNickname(nickname)) {
            errors.add(new FieldErrorItem("nickname", "Nickname already exists"));
        }

        if (password != null && password.contains(" ")) {
            errors.add(new FieldErrorItem("password", "Password cannot contain spaces"));
        }

        if (emailAddress == null || emailAddress.isBlank()) {
            errors.add(new FieldErrorItem("emailAddress", "Email cannot be blank"));
        } else if (userLoginDataRepository.existsByEmailAddress(emailAddress)) {
            errors.add(new FieldErrorItem("emailAddress", "Email already exists"));
        }

        if (!errors.isEmpty()) {
            throw new BusinessValidationException("Input has invalid fields", errors);
        }
    }

    public void validateOnLogin(UserLoginRequestDto userRequest) {
        List<FieldErrorItem> errors = new ArrayList<>();
        final String email = userRequest.getEmailAddress();
        final String password = userRequest.getPassword();

        if (email == null || email.isBlank()) {
            errors.add(new FieldErrorItem("emailAddress", "Email cannot be blank"));
        } else if (!userLoginDataRepository.existsByEmailAddress(email)) {
            errors.add(new FieldErrorItem("emailAddress", "Email address does not exist"));
        }

        if (errors.isEmpty()) {
            UserLoginData savedUserLoginData = userLoginDataRepository.findByEmailAddress(email);
            if (savedUserLoginData == null) {
                errors.add(new FieldErrorItem("emailAddress", "Email address not found"));
            } else {
                if (!securityUtil.verifyPassword(password, savedUserLoginData.getPasswordHash())) {
                    errors.add(new FieldErrorItem("password", "Password does not match"));
                }
            }
        }

        if (!errors.isEmpty()) {
            throw new BusinessValidationException("Input has invalid fields", errors);
        }
    }
}
