package com.example.auth_task_api.api.dto.Auth;

import com.fasterxml.jackson.annotation.JsonProperty;
public record LogoutRequest(
        @JsonProperty("session_token")
        String sessionToken
) {
}
