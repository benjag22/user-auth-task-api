package com.example.auth_task_api.Security;

public class SecurityConstrants {
    public static final long EXPIRATION_TIME = 432_000_000; // 5 days
    public static final String SECRET = "oursecretkey";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
