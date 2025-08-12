package com.example.auth_task_api.api.validate;

import com.example.auth_task_api.api.dto.Users.UsersCreateRequestDto;
import com.example.auth_task_api.api.exceptions.BusinessValidationException;
import com.example.auth_task_api.api.exceptions.DuplicateResourceException;
import com.example.auth_task_api.api.exceptions.FieldErrorItem;
import com.example.auth_task_api.persistence.repository.UserLoginDataRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserLoginDataValidator {

    private final UserLoginDataRepository userLoginDataRepository;

    public UserLoginDataValidator(UserLoginDataRepository userLoginDataRepository) {
        this.userLoginDataRepository = userLoginDataRepository;
    }

    public void validateOnCreate(UsersCreateRequestDto userRequest) {

        List<FieldErrorItem> errors = new ArrayList<>();

        final String emailAdress = userRequest.getEmailAddress();
        final String nickname = userRequest.getNickname();

        if (nickname != null && nickname.isBlank()) {
            errors.add(new FieldErrorItem("nickname", "Nickname cannot be blank", "NICKNAME_BLANK"));
        }

        if (userRequest.getPassword() != null && userRequest.getPassword().contains(" ")) {
            errors.add(new FieldErrorItem("password", "Password cannot contain spaces", "PASSWORD_SPACES"));
        }
        if (!errors.isEmpty()) {
            throw new BusinessValidationException(
                    "Input has invalid fields",
                    errors
            );
        }
        checkNicknameUniqueness(nickname);

        checkEmailUniqueness(emailAdress);
    }

    private void checkNicknameUniqueness(String nickname) {
        if (nickname == null || nickname.isBlank()) return;
        boolean exists = userLoginDataRepository.existsByNickname(nickname);
        if (exists) {
            throw new DuplicateResourceException("nickname", "Nickname already in use");
        }
    }

    private void checkEmailUniqueness(String email) {
        if (email == null || email.isBlank()) return;
        boolean exists = userLoginDataRepository.existsByEmailAddress(email);
        if (exists) {
            throw new DuplicateResourceException("email", "Email already in use");
        }
    }
}
