package com.example.auth_task_api.service;

import com.example.auth_task_api.persistence.model.UserLoginData;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Service
public class JWTService {
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    @Value("${application.security.jwt.expiration}")
    private long expiration;


    public String generateToken(UserLoginData userLoginData) {
        return buildToken(userLoginData, expiration);
    }

    private String buildToken(final UserLoginData userLoginData, final long expiration) {
        return Jwts.builder()
                .id(userLoginData.getId().toString())
                .claims(Map.of(
                        "nickname", userLoginData.getNickname()
                ))
                .subject(userLoginData.getEmailAdress())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSecretKey())
                .compact();

    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
