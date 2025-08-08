package com.example.auth_task_api.Security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class SecurityUtil {
    private final BCryptPasswordEncoder passwordEncoder;
    private final SecureRandom secureRandom;

    public SecurityUtil() {
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.secureRandom = new SecureRandom();
    }

    public String generateSalt(){
        byte[] salt = new byte[16];
        secureRandom.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public String hashPassword(String password, String salt){
        return this.passwordEncoder.encode(password+salt);
    }

    public boolean verifyPassword(String password, String hashedPassword, String salt){
        return this.passwordEncoder.matches(password+salt, hashedPassword);
    }
}
