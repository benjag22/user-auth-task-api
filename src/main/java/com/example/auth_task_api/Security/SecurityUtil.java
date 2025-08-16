package com.example.auth_task_api.Security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class SecurityUtil {
    private final BCryptPasswordEncoder passwordEncoder;

    public SecurityUtil() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public String hashPassword(String password){
        return passwordEncoder.encode(password);
    }

    public boolean verifyPassword(String password, String hashedPassword){
        return passwordEncoder.matches(password, hashedPassword);
    }
}
